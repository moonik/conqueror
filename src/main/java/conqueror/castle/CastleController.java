package conqueror.castle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/castle/")
public class CastleController {

    @Autowired
    private CastleService castleService;

    @PostMapping("create")
    public Castle create(@RequestParam("name") String name) {

        Castle newCastle = castleService.createCastle(name);

        return newCastle;
    }

}
