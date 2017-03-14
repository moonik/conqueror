package conqueror.castle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/castle/")
public class CastleController {

    @Autowired
    private CastleRepository castleRepository;

    @Autowired
    private CastleRepo castleRepo;

    private int i, j, x, y;

    @PostMapping("create")
    public Castle create(@RequestParam("name") String name)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUser = auth.getName();
        Castle lastCastle = castleRepo.findAllOrderByXDesc();
        if(lastCastle == null) {
            x = (0 * 10) + (int) (1 + (int) (Math.random() * 10));
            y = (0 * 10) + (int) (1 + (int) (Math.random() * 10));
            Castle newCastle = new Castle(currentUser, name, x, y);
            return castleRepository.save(newCastle);
        }
        else if(lastCastle.getX() == 50){
                i = 0;
                j = lastCastle.getY() / 10 + 1;

                x = (i * 10) + (int) (1 + (int) (Math.random() * 10));
                y = (j * 10) + (int) (1 + (int) (Math.random() * 10));
                Castle newCastle = new Castle(currentUser, name, x, y);

                return castleRepository.save(newCastle);
            }
            else {
                i = lastCastle.getX()/10 + 1;
                j = lastCastle.getY();
                x = (i * 10) + (int) (1 + (int) (Math.random() * 10));
                y = (j * 10) + (int) (1 + (int) (Math.random() * 10));
                Castle newCastle = new Castle(currentUser, name, x, y);

                return castleRepository.save(newCastle);
            }
        }

}
