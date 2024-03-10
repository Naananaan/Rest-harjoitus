package com.naananaan.peli;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import com.naananaan.peli.Harrastus.HarrastusType;
import com.naananaan.peli.Herkut.HerkutType;
//import java.util.Map;
//import java.util.HashMap;
//import java.util.Random;

@RestController

public class KoirapeliRestController {


    private Koira game;
    private KoiranAsiat koiranAsiat;

    private boolean checkBudjetti(Harrastus oldHarrastus, Harrastus updatedHarrastus) {
        double budjetti = koiranAsiat.getBudjetti();
        double oldMenot = oldHarrastus.getMenot(game);
        double updatedMenot = updatedHarrastus.getMenot(game);
        return budjetti - oldMenot + updatedMenot >= 0;
    }

    @GetMapping("/")
    public String info() {
        return "******KOIRAPELI*******<br><br>\n\n" +
        "----- (info) - peli selitettynä--- <br>\n"+
        "-----Kun aloitat pelaamisen, Anna ensin koiran rotu, nimi, hihnan ja valjaiden väri sekä käytössä oleva budjetti--- <br>\n"+
        "-----Voit aloittaa valitsemaan koirallesi harrastuksen sekä herkut --- <br>\n" +
        "-----Voit tarvittaessa muokata sisustusvalintojasi--- <br>\n"+
        "-----Käytettävissä oleva budjetti vähenee valintojesi edetessä --- <br>\n"+
        "----- --- <br>\n"+
        "-----Mukavia hetki koirasi kanssa --- <br>\n"+

        "***** KOMENNOT ***** <br><br><br>\n\n"+

        "-----Aloita peli kutsumalla: /start <br>\n" +
        "-----Anna tiedot: rotu, nimi, ulkoilu, budjetti <br>\n" +
        "<br>\n **** GET <br>\n" +
        "-----Kutsu koira: /koira --- <br>\n" +
        "-----Kutsu budjetti: /budjetti --- <br>\n" +
        "-----Kutsu herkut: /herkut --- <br>\n" +
        "-----Kutsu harrastus: /harrastus --- <br>\n" +
        "-----Lista valittavista herkuista: /herkut/types --- <br>\n"+
        "-----Lista valittavista harrastuksista: /harrastus/types --- <br>\n";


    }

    @PostMapping("/aloita")
    public String startGame(@RequestBody StartGameRequest request) {
        this.game = new Koira(String.valueOf(request.rotu()), String.valueOf(request.koiranNimi()), String.valueOf(request.ulkoilu()), request.budjetti(), 0.0);
        this.koiranAsiat = new KoiranAsiat(game, request.budjetti());
        return "Valitsit rodun: " + game.getRotu() + ". Annoit hänelle nimen : " + game.getKoiranNimi() + " ja sille valitsit " + game.getUlkoilu() + " valjaat ja hihnan. Nyt pääset hoitamaan koiraasi! Sinulla on käytettävissä: " + koiranAsiat.getBudjetti() + "euroa.";
    }

    

    @GetMapping("/budjetti")
    public double getBudjetti() {
        return koiranAsiat.getBudjetti() - koiranAsiat.getKoira().getMenot(); 
    }
    @GetMapping("/herkut") 
    public List <Herkut> getHerkut() {
        return koiranAsiat.getKoira().getHerkuts();
    }
    @GetMapping("/harrastus") 
    public Harrastus getHarrastus() {
        return koiranAsiat.getKoira().getHarrastus();
    }

    @GetMapping("/koira")
    public Koira getKoira() {
    return koiranAsiat.getKoira(); 
}



@GetMapping("/herkut/types")
    public List<HerkutType> getHerkutTypes() {
        return koiranAsiat.getAvailableHerkutTypes();
    }

    @GetMapping("/harrastus/types")
    public List<HarrastusType> getHarrastusTypes() {
        return koiranAsiat.getAvailableHarrastusTypes();
    }

    private boolean checkBudjetti(ValitutTuotteet oldHerkut, ValitutTuotteet updatedHerkut) {
        double budjetti = koiranAsiat.getBudjetti();
        budjetti -= koiranAsiat.getKoira().getMenot();

        double delta = updatedHerkut.getMenot(koiranAsiat.getKoira()) - oldHerkut.getMenot(koiranAsiat.getKoira());
        budjetti -= delta;
        return budjetti >= 0;
    }

}