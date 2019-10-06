package pl.javastart.sellegro.auction.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.javastart.sellegro.auction.entity.Auction;
import pl.javastart.sellegro.auction.filter.AuctionFilters;
import pl.javastart.sellegro.auction.repository.AuctionRepository;

import java.util.List;

@Service
public class AuctionService {

    private AuctionRepository auctionRepository;

    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public List<Auction> getSortedAuctions(String sort) {
        List<Auction> auctions;
        switch (sort) {
            case "carMake":
                auctions = auctionRepository.findAllByOrderByCarMake();
                break;
            case "carModel":
                auctions = auctionRepository.findAllByOrderByCarModel();
                break;
            case "price":
                auctions = auctionRepository.findAllByOrderByPrice();
                break;
            case "color":
                auctions = auctionRepository.findAllByOrderByColor();
                break;
            case "endDate":
                auctions = auctionRepository.findAllByOrderByEndDate();
                break;
            default:
                auctions = auctionRepository.findAll();
                break;
        }
        return auctions;
    }

    public List<Auction> getFilteredAuctions(AuctionFilters auctionFilters) {
        List<Auction> auctions;
        String carMaker = auctionFilters.getCarMaker();
        String carModel = auctionFilters.getCarModel();
        String color = auctionFilters.getColor();

        if (!StringUtils.isEmpty(carMaker)) {
            auctions = auctionRepository.findByCarMakeIgnoreCase(carMaker);
        } else if (!StringUtils.isEmpty(carModel)) {
            auctions = auctionRepository.findByCarModelIgnoreCase(carModel);
        } else if (!StringUtils.isEmpty(color)) {
            auctions = auctionRepository.findByColorIgnoreCase(color);
        } else {
            auctions = auctionRepository.findAll();
        }
        return auctions;
    }
}
