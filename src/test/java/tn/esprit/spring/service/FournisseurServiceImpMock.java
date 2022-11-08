package tn.esprit.spring.service;




import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.AchatApplication;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.FournisseurRepository;
import tn.esprit.rh.achat.services.FournisseurServiceImpl;

@SpringBootTest(classes = AchatApplication.class)
@ExtendWith(MockitoExtension.class)


public class FournisseurServiceImpMock {
	
	@Mock
	FournisseurRepository fournisseurRepository;
	
	@InjectMocks
	FournisseurServiceImpl fournisseurService;
	Fournisseur fournisseurs = new Fournisseur ("R6","Achats de marchandises",CategorieFournisseur.CONVENTIONNE);
	List<Fournisseur> listfournisseurs = new ArrayList<Fournisseur>() {
		{
		add(new Fournisseur ("R1","Banques",CategorieFournisseur.CONVENTIONNE));
	}
	};
	
	
	
	@Test
	public void testRetrieveFournisseur() {
	Mockito.when(fournisseurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(fournisseurs));
	Fournisseur fournisseur1 = fournisseurService.retrieveFournisseur(2L);
	Assertions.assertNotNull(fournisseur1);
	}
	
	/*
	public void testAddFournisseur()throws ParseException {
		Fournisseur fournisseurs = new Fournisseur("R1","Banques",CategorieFournisseur.CONVENTIONNE);
		assertTrue(fournisseurs.getIdFournisseur().equals(fournisseurs));
		fournisseurService.addFournisseur(fournisseurs);
	}
	*/
	
	@Test
    void testRetrieveAllFournisseurs() {
         
        Mockito.doReturn(listfournisseurs).when(fournisseurRepository).findAll();
        List<Fournisseur> actualFournisseur = fournisseurService.retrieveAllFournisseurs();
        assertThat(actualFournisseur).isEqualTo(listfournisseurs);
    }



   @Test
    void testAddFournisseur() {
        
       
        Mockito.when(fournisseurRepository.save(Mockito.any(Fournisseur.class))).thenReturn(fournisseurs);
        Fournisseur Fournisseurs = fournisseurService.addFournisseur(fournisseurs);
        assertNotNull(Fournisseurs);
        assertEquals(Fournisseurs, fournisseurs);



   }
   
   
   @Test
	public void removeFournisseurById_whenDeleteMethod() throws Exception {
	   Fournisseur fournisseurs = new Fournisseur();
	//user.setName(“Test Name”);
	   fournisseurs.setIdFournisseur (89L);
	doNothing().when(fournisseurService).deleteFournisseur(fournisseurs.getIdFournisseur());

	}
   
    /*
    @Test
    void testDeleteFournisseur()  {
    	fournisseurService.deleteFournisseur((long) 1);;
        Mockito.verify(fournisseurRepository, times(1)).deleteById((long) 1);
    }
*/

   @Test
    void testUpdateCategorieFournisseur() {
        Mockito.when(fournisseurRepository.save(Mockito.any(Fournisseur.class))).thenReturn(fournisseurs);
        fournisseurs.setCode("R6");
        Fournisseur CF = fournisseurService.updateFournisseur(fournisseurs) ;
        
        assertNotNull(CF);
        assertEquals("R6", fournisseurs.getCode());
    }
	
	
}
