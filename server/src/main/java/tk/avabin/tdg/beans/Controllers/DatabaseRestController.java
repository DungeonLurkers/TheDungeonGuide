package tk.avabin.tdg.beans.Controllers;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;
import tk.avabin.tdg.Util;
import tk.avabin.tdg.beans.Entities.Character;
import tk.avabin.tdg.beans.Entities.*;
import tk.avabin.tdg.beans.Services.*;

import java.util.ArrayList;
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
            @RequestParam("id") Long id,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("characters") String characters) {
        ArrayList ids = new ArrayList<Long>();
        HashSet<Character> chars = new HashSet<>();
        Arrays.stream(characters.split(";")).forEach(
                s -> chars.add(ctx.getBean(CharacterServiceImpl.class).getById(Long.valueOf(s).intValue()))
        );

        User u = ctx.getBean(User.class);
        u.setId(id);
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(password);
        u.setCharacters(chars);
        ctx.getBean(UserServiceImpl.class).saveOrUpdate(u);

        return Util.prettyStringFromObject(u);
    }

    @RequestMapping(value = "/createcharacter", method = RequestMethod.GET)
    public @ResponseBody
    String createCharacterInDatabase(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("ownerid") Long ownerId,
            @RequestParam("session") Long sessionId,
            @RequestParam("items") String itemsId,
            @RequestParam("spells") String spellsId) {

        HashSet<Item> items = new HashSet<>();
        HashSet<Spell> spells = new HashSet<>();

        Arrays.stream(itemsId.split(";")).forEach(
                s -> items.add(ctx.getBean(ItemServiceImpl.class).getById(Long.valueOf(s).intValue()))
        );
        Arrays.stream(spellsId.split(";")).forEach(
                s -> spells.add(ctx.getBean(SpellServiceImpl.class).getById(Long.valueOf(s).intValue()))
        );

        Character c = ctx.getBean(Character.class);
        c.setId(id);
        c.setName(name);
        c.setOwner(ctx.getBean(UserServiceImpl.class).getById(ownerId.intValue()));
        c.setSession(ctx.getBean(RPGSessionServiceImpl.class).getById(sessionId.intValue()));
        c.setItems(items);
        c.setSpells(spells);
        ctx.getBean(CharacterServiceImpl.class).saveOrUpdate(c);

        return Util.prettyStringFromObject(c);
    }

    @RequestMapping(value = "/createsession", method = RequestMethod.GET)
    public @ResponseBody
    String createRPGSessionInDatabase(
            @RequestParam("id") Long id,
            @RequestParam("name") String name,
            @RequestParam("gamemaster") Long masterId,
            @RequestParam("players") String playersIds,
            @RequestParam("characters") String charsIds) {

        HashSet<User> users = new HashSet<>();
        HashSet<Character> chars = new HashSet<>();

        Arrays.stream(playersIds.split(";")).forEach(
                s -> users.add(ctx.getBean(UserServiceImpl.class).getById(Long.valueOf(s).intValue()))
        );
        Arrays.stream(charsIds.split(";")).forEach(
                s -> chars.add(ctx.getBean(CharacterServiceImpl.class).getById(Long.valueOf(s).intValue()))
        );

        RPGSession c = ctx.getBean(RPGSession.class);
        c.setId(id);
        c.setName(name);
        c.setGameMaster(ctx.getBean(UserServiceImpl.class).getById(masterId.intValue()));
        c.setCharacters(chars);
        c.setPlayers(users);
        ctx.getBean(CharacterServiceImpl.class).saveOrUpdate(c);

        return Util.prettyStringFromObject(c);
    }
}
