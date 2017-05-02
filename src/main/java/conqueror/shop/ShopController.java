package conqueror.shop;

import conqueror.army.Army;
import conqueror.castle.Castle;
import conqueror.castle.CastleRepository;
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

    @Autowired
    private CastleRepository castleRepository;

    /**
     * buy new army
     * @param castleId castle id
     * @param shopDTO
     * @return new army
     */
    @PostMapping("buy/{castleId}")
    public Army buy(@PathVariable("castleId") Long castleId, ShopDTO shopDTO)
    {
        Castle castle = castleRepository.findOne(castleId);
        Army army = shopService.buyArmy(castle, shopDTO);
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
