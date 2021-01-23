package com.example.tacocloud.web;
import com.example.tacocloud.Ingredient;
import com.example.tacocloud.Taco;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {
    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
            new Ingredient("FLTO", "pszenna", Ingredient.Type.WRAP),
            new Ingredient("COTO", "kukurydziana", Ingredient.Type.WRAP),
            new Ingredient("GRBF", "mielona wołowina ", Ingredient.Type.PROTEIN),
            new Ingredient("CARN", "kawałki mięsa", Ingredient.Type.PROTEIN),
            new Ingredient("TMTO", "pomidory pokrojone w kostkę", Ingredient.Type.VEGGIES),
            new Ingredient("LETC", "sałata", Ingredient.Type.VEGGIES),
            new Ingredient("CHED", "cheddar", Ingredient.Type.CHEESE),
            new Ingredient("JACK", "Monterey Jack", Ingredient.Type.CHEESE),
            new Ingredient("SLSA", "pikantny sos pomidorowy", Ingredient.Type.SAUCE),
            new Ingredient("SRCR", "śmietana", Ingredient.Type.SAUCE)
        );
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("design", new Taco());
        return "design";
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}
