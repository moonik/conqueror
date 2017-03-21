package conqueror.shop;

import conqueror.army.Army;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/shop/")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @PostMapping("buy/{castleId}")
    public Army buy(@PathVariable("castleId") Long castleId, @RequestParam("amount") Long amount, @RequestParam("warrior") String warrior)
    {
        Army army = shopService.buyArmy(castleId, amount, warrior);
        return army;
    }

}
