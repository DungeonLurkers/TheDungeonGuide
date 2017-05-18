package tk.avabin.tdg.beans.Controllers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;
import tk.avabin.tdg.beans.Services.Base64SerializableProcessorService;
import tk.avabin.tdg.beans.Entities.Character;
import tk.avabin.tdg.beans.Entities.*;
import tk.avabin.tdg.beans.Services.*;
import tk.avabin.tdg.beans.Services.Implementations.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Avabin on 09.04.2017.
 */
@RestController
@Log
public class DatabaseRestController {
    private final PasswordEncryptionService passwordEncryptionService;

    private final SaltGeneratorService saltGeneratorService;

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

    private final Base64SerializableProcessorService base64SerializableProcessorService;

    @Autowired
    public DatabaseRestController(SaltGeneratorService saltGeneratorService,
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
                                  Base64SerializableProcessorService base64SerializableProcessorService,
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
    public @ResponseBody User testDB() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        String saltString = saltGeneratorService.nextSaltAsString();
        User u = ctx.getBean(User.class);
        u.setUsername("Admin1" + saltString);
        u.setSalt(saltString);
        u.setPassword(passwordEncryptionService.getEncryptedPassAsB64String("pass", saltString));
        u.setEmail("test@test.test");
        userService.saveOrUpdate(u);
        return u;
    }
    @RequestMapping("/api/checkusername")
    public @ResponseBody boolean checkUsernameInDatabase(
            @RequestParam("n") String username
    ) {
        return userService.getByUsername(username) != null;
    }
    
    @RequestMapping("/api/checkcharname")
    public @ResponseBody boolean chechCharacterNameInDatabase(
            @RequestParam("n") String name
    ) {
        return characterService.getByName(name) != null;
    }

    @RequestMapping("/admin")
    public String adminPage() {
        return "Yes, you are an admin.";
    }

    @RequestMapping(value = "/api/saveorupdate", method = RequestMethod.GET)
    public @ResponseBody
    Object createObjectInDatabase(
            @RequestParam("b64ob") String b64ob
    ) throws IOException, ClassNotFoundException, InvalidKeySpecException, NoSuchAlgorithmException {
        Object o = base64SerializableProcessorService.fromString(b64ob);
        Class obClass = o.getClass();
        if (obClass.equals(Character.class)) {
            characterService.saveOrUpdate((Character) o);
        }
        if (obClass.equals(CharacterAttack.class)) {
            characterAttackService.saveOrUpdate((CharacterAttack) o);
        }
        if (obClass.equals(Feat.class)) {
            featService.saveOrUpdate((Feat) o);
        }
        if (obClass.equals(Item.class)) {
            itemService.saveOrUpdate((Item) o);
        }
        if (obClass.equals(Language.class)) {
            languageService.saveOrUpdate((Language) o);
        }
        if (obClass.equals(RPGClassAndLevel.class)) {
            rpgClassAndLevelService.saveOrUpdate((RPGClassAndLevel) o);
        }
        if (obClass.equals(RPGSession.class)) {
            sessionService.saveOrUpdate((RPGSession) o);
        }
        if (obClass.equals(Skill.class)) {
            skillService.saveOrUpdate((Skill) o);
        }
        if (obClass.equals(Spell.class)) {
            spellService.saveOrUpdate((Spell) o);
        }
        if (obClass.equals(User.class)) {
            User u = (User) o;
            String saltString = saltGeneratorService.nextSaltAsString();
            u.setSalt(saltString);
            u.setPassword(Base64Utils.encodeToString(passwordEncryptionService.getEncryptedPass(u.getPassword(), saltString)));
            userService.saveOrUpdate((User) o);
        }
        return o;
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
