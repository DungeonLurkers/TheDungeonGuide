package tk.avabin.tdg.beans.Controllers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import tk.avabin.tdg.beans.Base64Processor;
import tk.avabin.tdg.beans.Entities.Character;
import tk.avabin.tdg.beans.Entities.*;
import tk.avabin.tdg.beans.Services.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    public DatabaseRestController(ApplicationContext ctx, CharacterServiceImpl characterService, CharacterAttackService characterAttackService, FeatService featService, LanguageService languageService, RPGClassAndLevelService rpgClassAndLevelService, UserServiceImpl userService, RPGSessionServiceImpl sessionService, ItemServiceImpl itemService, SkillService skillService, SpellServiceImpl spellService, Base64Processor base64Processor) {
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

    @RequestMapping("/admin")
    public String adminPage() {
        return "Yes, you are an admin.";
    }

    @RequestMapping(value = "/testdb", method = RequestMethod.GET)
    public @ResponseBody
    String testDb() {
        User nu1 = ctx.getBean(User.class);
        nu1.setUsername("Admin");
        nu1.setEmail("test@test.pl");
        nu1.setPassword("testpass");
        userService.saveOrUpdate(nu1);

        User nu2 = ctx.getBean(User.class);
        nu2.setUsername("Test1");
        nu2.setEmail("Test1@pl");
        nu2.setPassword("pass");
        userService.saveOrUpdate(nu2);

        RPGSession session = ctx.getBean(RPGSession.class);
        session.setName("TestSession");
        session.setGameMaster(nu1);
        Set<User> pl = new HashSet<>();
        pl.add(nu1);
        session.setPlayers(pl);
        Set<Character> ch = new HashSet<>();

        Character nu1ch = ctx.getBean(Character.class);

        nu1ch.setName("Test1Ch");
        nu1ch.setOwner(nu2);
        nu1ch.setItems(new HashSet<>());
        nu1ch.setSpells(new HashSet<>());
        characterService.saveOrUpdate(nu1ch);

        ch.add(nu1ch);

        session.setCharacters(ch);

        sessionService.saveOrUpdate(session);
        return sessionService.getByName(session.getName()).toString();
    }

    @RequestMapping(value = "/saveorupdate", method = RequestMethod.GET)
    public @ResponseBody
    String createObjectInDatabase(
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

        return o.toString();
    }

    @RequestMapping(value = "/createuser", method = RequestMethod.GET)
    public @ResponseBody
    String createUserInDatabase(
            @RequestParam(name = "base64object") String object) throws IOException, ClassNotFoundException {
        Object o = base64Processor.fromString(object);
        return o.toString();
    }

    @RequestMapping(value = "/createcharacter", method = RequestMethod.GET)
    public @ResponseBody
    String createCharacterInDatabase(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam("name") String name,
            @RequestParam("ownername") String owner,
            @RequestParam(name = "items", required = false) String itemNames,
            @RequestParam(name = "spells", required = false) String spellNames) {

        HashSet<Item> items = new HashSet<>();
        HashSet<Spell> spells = new HashSet<>();

        if (itemNames != null)
            Arrays.stream(itemNames.split(";")).forEach(
                    s -> items.add(ctx.getBean(ItemServiceImpl.class).getById(Integer.valueOf(s)))
            );
        if (spellNames != null)
            Arrays.stream(spellNames.split(";")).forEach(
                    s -> spells.add(ctx.getBean(SpellServiceImpl.class).getById(Integer.valueOf(s)))
            );

        Character c = ctx.getBean(Character.class);
        if (id != null) c.setId(id);
        c.setName(name);
        c.setOwner(ctx.getBean(UserServiceImpl.class).getByUsername(owner));
        c.setItems(items);
        c.setSpells(spells);
        characterService.saveOrUpdate(c);

        return c.toString();
    }

    @RequestMapping(value = "/createsession", method = RequestMethod.GET)
    public @ResponseBody
    String createRPGSessionInDatabase(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam("name") String name,
            @RequestParam("gmname") String gmName,
            @RequestParam(name = "players", required = false) String playersIds,
            @RequestParam(name = "characters", required = false) String charsIds) {

        HashSet<User> users = new HashSet<>();
        HashSet<Character> chars = new HashSet<>();
        if (playersIds != null)
            Arrays.stream(playersIds.split(";")).forEach(
                    s -> users.add(ctx.getBean(UserServiceImpl.class).getByUsername(s))
            );
        if (charsIds != null)
            Arrays.stream(charsIds.split(";")).forEach(
                    s -> chars.add(ctx.getBean(CharacterServiceImpl.class).getById(Integer.valueOf(s)))
            );

        RPGSession s = ctx.getBean(RPGSession.class);
        if (id != null) s.setId(id);
        s.setName(name);
        User master = ctx.getBean(UserServiceImpl.class).getByUsername(gmName);
        s.setGameMaster(master);
        s.setCharacters(chars);
        s.setPlayers(users);
        sessionService.saveOrUpdate(s);

        return s.toString();
    }

    @RequestMapping("/createitem")
    public @ResponseBody
    String createItemInDatabase(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam("name") String name,
            @RequestParam("price") Integer price,
            @RequestParam(name = "desc", required = false) String desc) {
        if (desc == null) desc = "";
        Item i = ctx.getBean(Item.class);
        if (id != null) i.setId(id);
        i.setName(name);
        i.setPrice(price);
        i.setDesc(desc);
        itemService.saveOrUpdate(i);
        return i.toString();
    }

    @RequestMapping("/createspell")
    public @ResponseBody
    String createSpellInDatabase(
            @RequestParam(name = "id", required = false) Integer id,
            @RequestParam("name") String name,
            @RequestParam("rank") Short rank,
            @RequestParam(name = "desc", required = false) String desc) {
        Spell s = ctx.getBean(Spell.class);
        if (id != null) s.setId(id);
        s.setName(name);
        s.setRank(rank);
        if (desc == null) desc = "";
        s.setDesc(desc);
        return s.toString();
    }
}
