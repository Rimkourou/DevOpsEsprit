package tn.esprit.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.AchatApplication;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;
import java.util.Optional;

@SpringBootTest(classes = AchatApplication.class)
@ExtendWith(MockitoExtension.class)
public class StockServiceImpMock {
	
	@Mock
	StockRepository stockRepository;
	
	@InjectMocks
	StockServiceImpl stockService;
	
	Stock stock=new Stock("StockInitial",5000,50);
	
	List<Stock> stocks= new ArrayList<Stock>() {
		{
			add (new Stock("StockInitial",4000,65));
			add (new Stock("annulation stock",6800,60));
		}
		
	};
	@Test
	public void testRetrieveStock() {
	Mockito.when(stockRepository.findById(stock.getIdStock())).thenReturn(Optional.of(stock));
		Stock stock1 = stockService.retrieveStock(2L);
		Assertions.assertNotNull(stock1);
	}
	
	//add
	@Test 
	public void testAddStock() {
		Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(stock);
        Stock NewSK = stockService.addStock(stock);
        assertNotNull(NewSK);
        assertEquals(NewSK, stock);
		
	}
	
	@Test
	public void removeUserById_whenDeleteMethod() throws Exception {
	Stock stock = new Stock();
	//user.setName(“Test Name”);
	stock.setIdStock(89L);
	doNothing().when(stockService).deleteStock(stock.getIdStock());

	}
	
	  @Test
	    void testUpdateStock() {
	        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(stock);
	        stock.setLibelleStock("stockInitial");
	        Stock exisitingCP = stockService.updateStock(stock) ;
	        
	        assertNotNull(exisitingCP);
	        assertEquals("stockInitial", stock.getLibelleStock());
	    }
	  
	  @Test
	    void testRetrieveAllStocks() {
	         
	        Mockito.doReturn(stocks).when(stockRepository).findAll();
	        List<Stock> actualStock = stockService.retrieveAllStocks();
	        assertThat(actualStock).isEqualTo(stocks);
	    }

	
	
	
	
	
	
};