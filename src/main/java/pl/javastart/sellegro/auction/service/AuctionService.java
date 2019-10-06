package pl.javastart.sellegro.auction.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import pl.javastart.sellegro.auction.entity.Auction;
import pl.javastart.sellegro.auction.filter.AuctionFilters;
import pl.javastart.sellegro.auction.repository.AuctionRepository;

import java.util.Collections;
import java.util.List;


@Service
public class AuctionService {

    private AuctionRepository auctionRepository;

    public AuctionService(AuctionRepository auctionRepository) {
        this.auctionRepository = auctionRepository;
    }

    public Page<Auction> findPaginated(Pageable pageable) {
        List<Auction> auctions = auctionRepository.findAll();
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Auction> list;

        if (auctions.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, auctions.size());
            list = auctions.subList(startItem, toIndex);
        }

        Page<Auction> auctionPage = new PageImpl<Auction>(list, PageRequest.of(currentPage, pageSize), auctions.size());
        return auctionPage;
    }

    public Page<Auction> getFilteredAuctions(AuctionFilters auctionFilters, Pageable pageable) {
        Page<Auction> auctions;
        String carMaker = auctionFilters.getCarMaker();
        String carModel = auctionFilters.getCarModel();
        String color = auctionFilters.getColor();

        if (!StringUtils.isEmpty(carMaker)) {
            auctions = auctionRepository.findByCarMakeIgnoreCase(carMaker, pageable);
        } else if (!StringUtils.isEmpty(carModel)) {
            auctions = auctionRepository.findByCarModelIgnoreCase(carModel, pageable);
        } else if (!StringUtils.isEmpty(color)) {
            auctions = auctionRepository.findByColorIgnoreCase(color, pageable);
        } else {
            auctions = auctionRepository.findAll(pageable);
        }
        return auctions;
    }
}
