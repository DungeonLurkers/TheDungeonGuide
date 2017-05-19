package tk.avabin.tdg.beans.Controllers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tk.avabin.tdg.beans.DTO.RPGSessionDto;
import tk.avabin.tdg.beans.Entities.Character;
import tk.avabin.tdg.beans.Entities.RPGSession;
import tk.avabin.tdg.beans.Entities.User;
import tk.avabin.tdg.beans.Services.DTO.GenericDtoService;
import tk.avabin.tdg.beans.Services.Entities.*;
import tk.avabin.tdg.beans.Services.Entities.Implemetations.*;
import tk.avabin.tdg.beans.Services.Implementations.Base64SerializableProcessorServiceImpl;
import tk.avabin.tdg.beans.Services.Implementations.SaltGeneratorServiceImpl;
import tk.avabin.tdg.beans.Services.PasswordEncryptionService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashSet;

/**
 * Created by Avabin on 09.04.2017.
 */
@RestController
@Log
public class DatabaseRestController {
    private final PasswordEncryptionService passwordEncryptionService;

    private final SaltGeneratorServiceImpl saltGeneratorService;

    private final ApplicationContext ctx;

    private final CharacterService characterService;

    private final CharacterAttackService characterAttackService;

    private final FeatService featService;

    private final ItemService itemService;

    private final LanguageService languageService;

    private final RPGClassAndLevelService rpgClassAndLevelService;

    private final RPGSessionService sessionService;

    private final SkillService skillService;

    private final UserService userService;

    private final SpellService spellService;

    private final Base64SerializableProcessorServiceImpl base64SerializableProcessorService;

    @Autowired
    public DatabaseRestController(SaltGeneratorServiceImpl saltGeneratorService,
                                  ApplicationContext ctx,
                                  CharacterServiceImpl characterService,
                                  CharacterAttackService characterAttackService,
                                  FeatService featService, LanguageService languageService,
                                  RPGClassAndLevelService rpgClassAndLevelService,
                                  UserServiceImpl userService,
                                  RPGSessionServiceImpl sessionService,
                                  ItemServiceImpl itemService,
                                  SkillService skillService,
                                  SpellServiceImpl spellService,
                                  Base64SerializableProcessorServiceImpl base64SerializableProcessorService,
                                  PasswordEncryptionService passwordEncryptionService) {
        this.saltGeneratorService = saltGeneratorService;
        this.ctx = ctx;
        this.characterService = characterService;
        this.characterAttackService = characterAttackService;
        this.featService = featService;
        this.languageService = languageService;
        this.rpgClassAndLevelService = rpgClassAndLevelService;
        this.userService = userService;
        this.sessionService = sessionService;
        this.itemService = itemService;
        this.skillService = skillService;
        this.spellService = spellService;
        this.base64SerializableProcessorService = base64SerializableProcessorService;
        this.passwordEncryptionService = passwordEncryptionService;
    }
    @RequestMapping("/test")
    public @ResponseBody String test() {
        User u = userService.getByUsername("Admin");
        if(u == null) {
            log.info("Admin account does not exists!");
            String saltString = null;
            try {
                saltString = saltGeneratorService.nextSaltAsString();
                u = ctx.getBean(User.class);
                u.setUsername("Admin");
                u.setEmail("admin@this");
                u.setSalt(saltString);
                try {
                    u.setPassword(passwordEncryptionService.getEncryptedPassAsB64String("testpass", saltString));
                    userService.saveOrUpdate(u);
                    log.info("Admin account created!");
                } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "Tested!";
    }

    @RequestMapping("/testsalt")
    public @ResponseBody String testSalt() throws IOException { return saltGeneratorService.nextSaltAsString(); }

    @RequestMapping("/testdb")
    public @ResponseBody
    Object testDB() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        HashSet<Character> chars = new HashSet<>();
        HashSet<User> players = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            Character c = ctx.getBean(Character.class);
            c.setId(i);
            c.setName("Lelo" + i);
            chars.add(c);
            User u = ctx.getBean(User.class);
            u.setId(i);
            u.setUsername("Woooop" + i);
            u.setEmail("test" + i + "@test.pl");
            u.setPassword("dupa123" + 1);
            u.setSalt("salt" + i);
            c.setOwner(u);
            players.add(u);
        }
        RPGSession session = ctx.getBean(RPGSession.class);
        session.setCharacters(chars);
        session.setPlayers(players);
        session.setGameMaster(userService.getByUsername("Admin"));
        session.setName("Test:o");
        GenericDtoService dtoService = ctx.getBean(GenericDtoService.class);
        return dtoService.convertEntityToDto(session, RPGSessionDto.class);
    }

    @RequestMapping("/admin")
    public String adminPage() {
        return "Yes, you are an admin.";
    }



    /*
    @RequestMapping(name = "/notlogged/auth*", method = RequestMethod.POST)
    public @ResponseBody boolean authenticate(
            @RequestParam("username") String username,
            @RequestParam("attpass") String attemptedPass
    ) {
        User u = userService.getByUsername(username);
        try {
            return passwordEncryptionService.authenticate(attemptedPass, u.getPassword(), u.getSalt());
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return false;
    }*/
}
