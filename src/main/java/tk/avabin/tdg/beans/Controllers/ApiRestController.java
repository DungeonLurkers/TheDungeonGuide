package tk.avabin.tdg.beans.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import tk.avabin.tdg.beans.Entities.Character;
import tk.avabin.tdg.beans.Entities.*;
import tk.avabin.tdg.beans.Services.Entities.*;
import tk.avabin.tdg.beans.Services.Implementations.Base64SerializableProcessorServiceImpl;
import tk.avabin.tdg.beans.Services.Implementations.SaltGeneratorServiceImpl;
import tk.avabin.tdg.beans.Services.PasswordEncryptionService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Created by Avabin on 18.05.2017.
 */
@RestController
public class ApiRestController {
    @Autowired
    private PasswordEncryptionService passwordEncryptionService;
    @Autowired
    private SaltGeneratorServiceImpl saltGeneratorService;
    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private CharacterService characterService;
    @Autowired
    private CharacterAttackService characterAttackService;
    @Autowired
    private FeatService featService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private LanguageService languageService;
    @Autowired
    private RPGClassAndLevelService rpgClassAndLevelService;
    @Autowired
    private RPGSessionService sessionService;
    @Autowired
    private SkillService skillService;
    @Autowired
    private UserService userService;
    @Autowired
    private SpellService spellService;
    @Autowired
    private Base64SerializableProcessorServiceImpl base64SerializableProcessorService;

    @RequestMapping("/api/checkusername")
    public @ResponseBody
    boolean checkUsernameInDatabase(
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
        return o;
    }

    @RequestMapping(value = "/api/createuser", method = RequestMethod.POST)
    public @ResponseBody
    User createUser(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {
        User u = new User();
        try {
            String salt = saltGeneratorService.nextSaltAsString();
            u.setUsername(username);
            u.setEmail(email);
            u.setSalt(salt);
            u.setPassword(passwordEncryptionService.getEncryptedPassAsB64String(password, salt));
            userService.saveOrUpdate(u);
        } catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return u;
    }


}
