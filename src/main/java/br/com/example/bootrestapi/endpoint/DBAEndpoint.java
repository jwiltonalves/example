package br.com.example.bootrestapi.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.example.bootrestapi.model.DBAEntity;
import br.com.example.bootrestapi.repository.DBARepository;

@RestController
@RequestMapping("/dba")
public class DBAEndpoint {
	
	@Autowired
	private DBARepository dbaRepository;
		
	@RequestMapping(path =  "/hello", method = RequestMethod.GET)
	private String helloJudeu() {
		return "Hello!";
	}
	
	
	@RequestMapping(path = "/tamanhoGalha/{tamanho}", method = RequestMethod.GET)
	private List<DBAEntity> listarPorTamanhoGalha(@PathVariable("tamanho") Integer tamanho){
		return dbaRepository.findAllByTamanhoGalha(tamanho);

	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> save(DBAEntity dba) {		
		try {
			dba.setId(null);
			if ( dba.getTamanhoGalha() == null || dba.getTamanhoGalha().toString().isEmpty() ) {
				throw new IllegalArgumentException("Impossível... DBA sem galhada não existe!");
			}
			DBAEntity persistente = dbaRepository.save(dba);
			return new ResponseEntity<>(persistente, HttpStatus.OK);
		}catch(IllegalArgumentException iae) {
			return new ResponseEntity<>(new String(iae.getMessage()), HttpStatus.FORBIDDEN);
		}
		catch(Exception e) {
			return new ResponseEntity<>(new String("Não foi possível registrar este animal de chifre por motivos inexplicáveis"), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	private ResponseEntity<?> listar(){
		List<DBAEntity> listaDba = dbaRepository.findAll();		
		if(listaDba == null || listaDba.isEmpty() )
			return new ResponseEntity<Object>(new String("Galhadas em falta!!!!"), HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(listaDba, HttpStatus.OK);		
	}
}
