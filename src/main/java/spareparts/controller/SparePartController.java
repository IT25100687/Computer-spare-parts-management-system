package spareparts.controller;

import org.springframework.web.bind.annotation.*;
import spareparts.service.SparePartService;
import spareparts.model.SparePart;
import java.util.List;

@RestController
@RequestMapping("/api/parts")
public class SparePartController {

    private final SparePartService sparePartService;

    public SparePartController(SparePartService sparePartService) {
        this.sparePartService = sparePartService;
    }

    @GetMapping
    public List<SparePart> getAllSpareParts() {
        return sparePartService.getAllSpareParts();
    }

    @GetMapping("/{id}")
    public SparePart getSparePartById(@PathVariable Long id) {
        return sparePartService.getSparePartById(id);
    }

    @PostMapping
    public SparePart createSparePart(@RequestBody SparePart sparePart) {
        return sparePartService.createSparePart(sparePart);
    }

    @PutMapping("/{id}")
    public SparePart updateSparePart(@PathVariable Long id, @RequestBody SparePart sparePart) {
        return sparePartService.updateSparePart(id, sparePart);
    }

    @DeleteMapping("/{id}")
    public String deleteSparePart(@PathVariable Long id) {
        sparePartService.deleteSparePart(id);
        return "Spare Part record #" + id + " deleted successfully.";
    }
}
