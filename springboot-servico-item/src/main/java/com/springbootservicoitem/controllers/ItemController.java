package com.springbootservicoitem.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.springbootservicoitem.models.Item;
import com.springboot.service.commons.models.Produto;
import com.springbootservicoitem.models.service.IItemService;

@RestController
@RefreshScope
public class ItemController {

	private static Logger log = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private Environment env;

	@Value("${configuracao.texto}")
	private String texto;

	@Autowired
	@Qualifier("serviceFeign") // tem duas implementações, @Qualifier defini qual usar
	private IItemService itemService;

	@GetMapping("/listar")
	public List<Item> listar() {
		return itemService.findAll();
	}

	@HystrixCommand(fallbackMethod = "metodoAlternativo")
	@GetMapping("/listar/{id}/quantidade/{quantidade}")
	public Item getItem(@PathVariable Long id, @PathVariable Integer quantidade) {
		return itemService.findById(id, quantidade);
	}

	public Item metodoAlternativo(Long id, Integer quantidade) {

		Produto produto = new Produto();
		Item item = new Item();

		item.setQuantidade(quantidade);
		produto.setId(id);
		produto.setNome("Produto Alternativo");
		produto.setPreco(10.0);

		item.setProduto(produto);

		return item;
	}

	@GetMapping("/obter-config")
	public ResponseEntity<?> obterConfig(@Value("${server.port}") String porta) {

		log.info(texto);

		Map<String, String> json = new HashMap<String, String>();
		json.put("texto", texto);
		json.put("porta", porta);

		if (env.getActiveProfiles().length > 0 && env.getActiveProfiles()[0].equals("dev")) {

			json.put("autor.nome", env.getProperty("configuracao.autor.nome"));
			json.put("autor.email", env.getProperty("configuracao.autor.email"));

		}

		return new ResponseEntity<Map<String, String>>(json, HttpStatus.OK);
	}

	@PostMapping("/criar")
	@ResponseStatus(HttpStatus.CREATED)
	public Produto criar(@RequestBody Produto produto) {
		return itemService.save(produto);
	}

	@PutMapping("/editar/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Produto editar(@RequestBody Produto produto, @PathVariable Long id) {
		return itemService.update(produto, id);
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void eliminar(@PathVariable Long id) {
		itemService.eliminar(id);
	}
}
