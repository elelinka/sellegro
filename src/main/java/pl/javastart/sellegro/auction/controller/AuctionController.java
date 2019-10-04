package pl.javastart.sellegro.auction.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.javastart.sellegro.auction.filter.AuctionFilters;
import pl.javastart.sellegro.auction.repository.AuctionRepository;
import pl.javastart.sellegro.auction.entity.Auction;

import java.util.List;

@Controller
public class AuctionController {

    private AuctionRepository auctionRepository;

    public AuctionController(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    @GetMapping("/auctions")
    public String auctions(Model model,
                           @RequestParam(required = false) String sort,
                           AuctionFilters auctionFilters) {
        List<Auction> auctions;

        if (sort != null) {
            auctions = getSortedAuctions(sort);
        } else {
            auctions = getFilteredAuctions(auctionFilters);
        }

        model.addAttribute("cars", auctions);
        model.addAttribute("filters", auctionFilters);
        return "auctions";
    }

    private List<Auction> getSortedAuctions(String sort) {
        List<Auction> auctions;
        switch (sort) {
            case "carMake":
                auctions = auctionRepository.findAllAuctionsOrderByCarMake();
                break;
            case "carModel":
                auctions = auctionRepository.findAllAuctionsOrderByCarModel();
                break;
            case "price":
                auctions = auctionRepository.findAllAuctionsOrderByPrice();
                break;
            case "color":
                auctions = auctionRepository.findAllAuctionsOrderByColor();
                break;
            case "endDate":
                auctions = auctionRepository.findAllAuctionsOrderByEndDate();
                break;
            default:
                auctions = auctionRepository.findAll();
                break;
        }
        return auctions;
    }

    private List<Auction> getFilteredAuctions(AuctionFilters auctionFilters) {
        List<Auction> auctions;
        String carMaker = auctionFilters.getCarMaker();
        String carModel = auctionFilters.getCarModel();
        String color = auctionFilters.getColor();

        if (!StringUtils.isEmpty(carMaker)) {
            auctions = auctionRepository.findByCarMake(carMaker);
        } else if (!StringUtils.isEmpty(carModel)) {
            auctions = auctionRepository.findByCarModel(carModel);
        } else if (!StringUtils.isEmpty(color)) {
            auctions = auctionRepository.findByColor(color);
        } else {
            auctions = auctionRepository.findAll();
        }
        return auctions;
    }
}
