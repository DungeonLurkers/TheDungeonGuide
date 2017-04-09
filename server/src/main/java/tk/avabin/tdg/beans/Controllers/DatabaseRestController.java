package tk.avabin.tdg.beans.Controllers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import tk.avabin.tdg.beans.Entities.Character;
import tk.avabin.tdg.beans.Entities.*;
import tk.avabin.tdg.beans.Services.*;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Avabin on 09.04.2017.
 */
@RestController
@Log
public class DatabaseRestController {
    private final ApplicationContext ctx;

    @Autowired
    public DatabaseRestController(ApplicationContext ctx) {
        this.ctx = ctx;
    }

    @RequestMapping("/admin")
    public String adminPage() {
        return "Yes, you are an admin.";
    }

    @RequestMapping(value = "/createuser", method = RequestMethod.GET)
    public @ResponseBody
    String createUserInDatabase(
            @RequestParam("id") Integer id,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam(name = "characters", required = false) String characters,
            @RequestParam(name = "sessions", required = false) String sessions) {
        HashSet<Character> chars = new HashSet<>();
        HashSet<RPGSession> rpgSessions = new HashSet<>();

        if (characters != null)
            Arrays.stream(characters.split(";")).forEach(
                    s -> chars.add(ctx.getBean(CharacterServiceImpl.class).getById(Integer.valueOf(s)))
            );

        if (sessions != null)
            Arrays.stream(sessions.split(";")).forEach(
                    s -> rpgSessions.add(ctx.getBean(RPGSessionServiceImpl.class).getById(Integer.valueOf(s)))
            );

        User u = ctx.getBean(User.class);
        u.setId(id);
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(password);
        u.setCharacters(chars);
        u.setSessions(rpgSessions);
        ctx.getBean(UserServiceImpl.class).saveOrUpdate(u);

        return u.toString();
    }

    @RequestMapping(value = "/createcharacter", method = RequestMethod.GET)
    public @ResponseBody
    String createCharacterInDatabase(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("ownerid") Integer ownerId,
            @RequestParam(name = "items", required = false) String itemsId,
            @RequestParam(name = "spells", required = false) String spellsId) {

        HashSet<Item> items = new HashSet<>();
        HashSet<Spell> spells = new HashSet<>();

        if (itemsId != null)
            Arrays.stream(itemsId.split(";")).forEach(
                    s -> items.add(ctx.getBean(ItemServiceImpl.class).getById(Integer.valueOf(s)))
            );
        if (spellsId != null)
            Arrays.stream(spellsId.split(";")).forEach(
                    s -> spells.add(ctx.getBean(SpellServiceImpl.class).getById(Integer.valueOf(s)))
            );

        Character c = ctx.getBean(Character.class);
        c.setId(id);
        c.setName(name);
        c.setOwner(ctx.getBean(UserServiceImpl.class).getById(ownerId));
        c.setItems(items);
        c.setSpells(spells);
        ctx.getBean(CharacterServiceImpl.class).saveOrUpdate(c);

        return c.toString();
    }

    @RequestMapping(value = "/createsession", method = RequestMethod.GET)
    public @ResponseBody
    String createRPGSessionInDatabase(
            @RequestParam("id") Integer id,
            @RequestParam("name") String name,
            @RequestParam("gamemaster") Integer masterId,
            @RequestParam(name = "players", required = false) String playersIds,
            @RequestParam(name = "characters", required = false) String charsIds) {

        HashSet<User> users = new HashSet<>();
        HashSet<Character> chars = new HashSet<>();
        if (playersIds != null)
            Arrays.stream(playersIds.split(";")).forEach(
                    s -> users.add(ctx.getBean(UserServiceImpl.class).getById(Integer.valueOf(s)))
            );
        if (charsIds != null)
            Arrays.stream(charsIds.split(";")).forEach(
                    s -> chars.add(ctx.getBean(CharacterServiceImpl.class).getById(Integer.valueOf(s)))
            );

        RPGSession s = ctx.getBean(RPGSession.class);
        s.setId(id);
        s.setName(name);
        User master = ctx.getBean(UserServiceImpl.class).getById(1);
        s.setGameMaster(master);
        s.setCharacters(chars);
        s.setPlayers(users);
        ctx.getBean(RPGSessionServiceImpl.class).saveOrUpdate(s);

        return s.toString();
    }
}
