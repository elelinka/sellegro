package pl.javastart.sellegro.auction.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.sellegro.auction.entity.Auction;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    List<Auction> findAllByOrderByPriceDesc(Pageable pageable);

    List<Auction> findAllByOrderByCarMake();

    List<Auction> findAllByOrderByCarModel();

    List<Auction> findAllByOrderByPrice();

    List<Auction> findAllByOrderByColor();

    List<Auction> findAllByOrderByEndDate();

    List<Auction> findByCarMakeIgnoreCase(String carMake);

    List<Auction> findByCarModelIgnoreCase(String carModel);

    List<Auction> findByColorIgnoreCase(String color);
}
