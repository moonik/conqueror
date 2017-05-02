package conqueror.castle;

import conqueror.castle.exceptions.CastleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/castle/")
public class CastleController {

    @Autowired
    private CastleService castleService;

    @Autowired
    private CastleRepository castleRepository;

    /**
     * create castle
     * @param name castle name
     * @return saves castle
     */
    @PostMapping("create")
    public Castle create(@RequestParam(name = "name", required=false) String name) {

        Castle newCastle = castleService.createCastle(name);

        return newCastle;
    }

    /**
     * checks if user already has a castle
     * @return HttpStatus OK if user doesn't have the castle or Exception if he does
     */

    @GetMapping("checkCastle")
    public ResponseEntity<Castle> checkCastle()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();

        if(castleRepository.findOneByOwner(currentUser) != null)
        {
            throw new CastleException("User already has castle");
        }else
            return new ResponseEntity<Castle>(HttpStatus.OK);
    }

    @GetMapping("myCastles")
    public List<Castle> getMyCastles()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();

        return castleRepository.findByOwner(currentUser);
    }

    @GetMapping("castle/{id}")
    public Castle getCastle(@PathVariable("id") Long id)
    {
        return castleRepository.findOne(id);
    }

    @GetMapping("nearestCastles/{id}")
    public List<Castle> getCastles(@PathVariable("id") Long castleId)
    {
        return castleService.getNearestCastles(castleId);
    }


}
