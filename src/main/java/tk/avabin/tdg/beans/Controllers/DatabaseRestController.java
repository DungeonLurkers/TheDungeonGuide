package tk.avabin.tdg.beans.Controllers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.web.bind.annotation.*;
import tk.avabin.tdg.beans.Base64Processor;
import tk.avabin.tdg.beans.Entities.Character;
import tk.avabin.tdg.beans.Entities.*;
import tk.avabin.tdg.beans.Services.*;

import java.io.IOException;
import java.security.SecureRandom;

/**
 * Created by Avabin on 09.04.2017.
 */
@RestController
@Log
public class DatabaseRestController {
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

    private final Base64Processor base64Processor;

    @Autowired
    public DatabaseRestController(ApplicationContext ctx,
                                  CharacterServiceImpl characterService,
                                  CharacterAttackService characterAttackService,
                                  FeatService featService, LanguageService languageService,
                                  RPGClassAndLevelService rpgClassAndLevelService,
                                  UserServiceImpl userService,
                                  RPGSessionServiceImpl sessionService,
                                  ItemServiceImpl itemService,
                                  SkillService skillService,
                                  SpellServiceImpl spellService,
                                  Base64Processor base64Processor) {
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
        this.base64Processor = base64Processor;
    }

    @RequestMapping("/testdb")
    public @ResponseBody User testDB() {
        User u = ctx.getBean(User.class);
        u.setUsername("Admin" + KeyGenerators.secureRandom(8).toString());
        u.setSalt(KeyGenerators.secureRandom(32).toString());
        u.setPassword(Encryptors.text("adminpass", u.getSalt()).toString());
        userService.saveOrUpdate(u);
        return u;
    }

    @RequestMapping("/admin")
    public String adminPage() {
        return "Yes, you are an admin.";
    }

    @RequestMapping(value = "/saveorupdate", method = RequestMethod.GET)
    public @ResponseBody
    Object createObjectInDatabase(
            @RequestParam("b64ob") String b64ob
    ) throws IOException, ClassNotFoundException {
        Object o = base64Processor.fromString(b64ob);
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
            userService.saveOrUpdate((User) o);
        }

        return o;
    }
}
