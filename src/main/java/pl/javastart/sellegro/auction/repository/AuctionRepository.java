package pl.javastart.sellegro.auction.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.javastart.sellegro.auction.entity.Auction;


import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Query("SELECT a FROM Auction a ORDER BY a.price DESC")
    List<Auction> find4MostExpensive(Pageable pageable);

    @Query("SELECT a FROM Auction a ORDER BY a.carMake")
    List<Auction> findAllAuctionsOrderByCarMake();

    @Query("SELECT a FROM Auction a ORDER BY a.carModel")
    List<Auction> findAllAuctionsOrderByCarModel();

    @Query("SELECT a FROM Auction a ORDER BY a.price")
    List<Auction> findAllAuctionsOrderByPrice();

    @Query("SELECT a FROM Auction a ORDER BY a.color")
    List<Auction> findAllAuctionsOrderByColor();

    @Query("SELECT a FROM Auction a ORDER BY a.endDate")
    List<Auction> findAllAuctionsOrderByEndDate();

    List<Auction> findByCarMake(String carMake);

    List<Auction> findByCarModel(String carModel);

    List<Auction> findByColor(String color);
}
