package conqueror.shop;

import conqueror.army.Army;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/shop/")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopRepository shopRepository;

    /**
     * buy new army
     * @param castleId castle id
     * @param shopDTO
     * @return new army
     */
    @PostMapping("buy/{castleId}")
    public Army buy(@PathVariable("castleId") Long castleId, ShopDTO shopDTO)
    {
        Army army = shopService.buyArmy(castleId, shopDTO);
        return army;
    }

    /**
     * gets shop items
     * @return gold
     */
    @GetMapping("get")
    public List<Shop> getGoods()
    {
        return shopRepository.findAll();
    }

}
