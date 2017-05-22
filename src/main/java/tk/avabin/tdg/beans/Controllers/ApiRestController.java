package tk.avabin.tdg.beans.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.avabin.tdg.beans.DTO.*;
import tk.avabin.tdg.beans.Entities.Character;
import tk.avabin.tdg.beans.Entities.*;
import tk.avabin.tdg.beans.Services.DTO.GenericDtoService;
import tk.avabin.tdg.beans.Services.Entities.*;
import tk.avabin.tdg.beans.Services.Implementations.Base64SerializableProcessorServiceImpl;
import tk.avabin.tdg.beans.Services.PasswordEncryptionService;
import tk.avabin.tdg.beans.Services.SaltGeneratorService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author Avabin
 */
@RestController()
@RequestMapping("/api")
public class ApiRestController {
    private final GenericDtoService dtoService;
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
    private final SaltGeneratorService saltGeneratorService;
    private final PasswordEncryptionService passwordEncryptionService;

    @Autowired
    public ApiRestController(CharacterService characterService,
                             Base64SerializableProcessorServiceImpl base64SerializableProcessorService,
                             UserService userService, GenericDtoService dtoService,
                             CharacterAttackService characterAttackService,
                             FeatService featService, ItemService itemService,
                             LanguageService languageService,
                             RPGClassAndLevelService rpgClassAndLevelService,
                             RPGSessionService sessionService,
                             SkillService skillService,
                             SpellService spellService,
                             SaltGeneratorService saltGeneratorService,
                             PasswordEncryptionService passwordEncryptionService) {
        this.characterService = characterService;
        this.base64SerializableProcessorService = base64SerializableProcessorService;
        this.userService = userService;
        this.dtoService = dtoService;
        this.characterAttackService = characterAttackService;
        this.featService = featService;
        this.itemService = itemService;
        this.languageService = languageService;
        this.rpgClassAndLevelService = rpgClassAndLevelService;
        this.sessionService = sessionService;
        this.skillService = skillService;
        this.spellService = spellService;
        this.saltGeneratorService = saltGeneratorService;
        this.passwordEncryptionService = passwordEncryptionService;
    }

    @RequestMapping("/checkName/user")
    public @ResponseBody
    boolean checkUsernameInDatabase(
            @RequestParam("n") String username
    ) {
        return userService.getByUsername(username) != null;
    }

    @RequestMapping("/checkName/character")
    public @ResponseBody boolean chechCharacterNameInDatabase(
            @RequestParam("n") String name
    ) {
        return characterService.getByName(name) != null;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    String createObjectInDatabase(
            @RequestParam("o") String o
    ) {
        Object i = null;
        try {
            i = base64SerializableProcessorService.fromString(o);
        } catch (IOException e) {
            e.printStackTrace();
            return "IO Exception!";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return "Class not found!";
        }
        Class iClass = i.getClass();
        if (iClass.equals(CharacterDto.class))
            characterService.saveOrUpdate((Character) dtoService.convertDtoToEntity(i, Character.class));
        if (iClass.equals(CharacterAttackDto.class))
            characterAttackService.saveOrUpdate((CharacterAttack) dtoService.convertDtoToEntity(i, CharacterAttack.class));
        if (iClass.equals(FeatDto.class))
            featService.saveOrUpdate((Feat) dtoService.convertDtoToEntity(i, Feat.class));
        if (iClass.equals(ItemDto.class))
            itemService.saveOrUpdate((Item) dtoService.convertDtoToEntity(i, Item.class));
        if (iClass.equals(LanguageDto.class))
            languageService.saveOrUpdate((Language) dtoService.convertDtoToEntity(i, Language.class));
        if (iClass.equals(RPGClassAndLevelDto.class))
            rpgClassAndLevelService.saveOrUpdate((RPGClassAndLevel) dtoService.convertDtoToEntity(i, RPGClassAndLevel.class));
        if (iClass.equals(RPGSessionDto.class))
            sessionService.saveOrUpdate((RPGSession) dtoService.convertDtoToEntity(i, RPGSession.class));
        if (iClass.equals(SkillDto.class))
            skillService.saveOrUpdate((Skill) dtoService.convertDtoToEntity(i, Skill.class));
        if (iClass.equals(SpellDto.class))
            spellService.saveOrUpdate((Spell) dtoService.convertDtoToEntity(i, Spell.class));
        if (iClass.equals(UserDto.class)) {
            User u = (User) dtoService.convertDtoToEntity(i, User.class);
            try {
                u.setSalt(saltGeneratorService.nextSaltAsString());
            } catch (IOException e) {
                e.printStackTrace();
                return "IO Exception!";
            }
            try {
                u.setPassword(passwordEncryptionService.getEncryptedPassAsB64String(u.getPassword(), u.getSalt()));
            } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            userService.saveOrUpdate(u);
        }
        return "ok";
    }
}
