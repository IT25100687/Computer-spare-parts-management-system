package spareparts.controller;

import org.springframework.web.bind.annotation.*;
import spareparts.service.WarrantyService;
import spareparts.model.Warranty;
import java.util.List;

@RestController
@RequestMapping("/api/warranties")
public class WarrantyController {

    private final WarrantyService warrantyService;

    public WarrantyController(WarrantyService warrantyService) {
        this.warrantyService = warrantyService;
    }

    @GetMapping
    public List<Warranty> getAllWarranties() {
        return warrantyService.getAllWarranties();
    }

    @GetMapping("/{id}")
    public Warranty getWarrantyById(@PathVariable Long id) {
        return warrantyService.getWarrantyById(id);
    }

    @PostMapping
    public Warranty createWarranty(@RequestBody Warranty warranty) {
        return warrantyService.createWarranty(warranty);
    }

    @PutMapping("/{id}")
    public Warranty updateWarranty(@PathVariable Long id, @RequestBody Warranty warranty) {
        return warrantyService.updateWarranty(id, warranty);
    }

    @DeleteMapping("/{id}")
    public String deleteWarranty(@PathVariable Long id) {
        warrantyService.deleteWarranty(id);
        return "Warranty record #" + id + " deleted successfully.";
    }
}
