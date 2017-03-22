package conqueror.castle;

import conqueror.castle.exceptions.CastleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/castle/")
public class CastleController {

    @Autowired
    private CastleService castleService;

    @Autowired
    private CastleRepository castleRepository;

    @PostMapping("create")
    public Castle create(@RequestParam(name = "name", required=false) String name) {

        Castle newCastle = castleService.createCastle(name);

        return newCastle;
    }

    @GetMapping("checkCastle")
    public ResponseEntity<Castle> checkCastle()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();

        if(castleRepository.findOneByOwner(currentUser) != null)
        {
            throw new CastleException();
        }else
            return new ResponseEntity<Castle>(HttpStatus.OK);
    }

}
