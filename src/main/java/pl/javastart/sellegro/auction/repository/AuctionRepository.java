package pl.javastart.sellegro.auction.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.javastart.sellegro.auction.entity.Auction;


import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Query("SELECT a FROM Auction a ")
    Page<Auction> findAll(Pageable pageable);

    @Query("SELECT a FROM Auction a")
    Page<Auction> findAllSorted(Pageable pageable);

    Page<Auction> findByCarMakeIgnoreCase(String carMake, Pageable pageable);

    Page<Auction> findByCarModelIgnoreCase(String carModel, Pageable pageable);

    Page<Auction> findByColorIgnoreCase(String color, Pageable pageable);
}
