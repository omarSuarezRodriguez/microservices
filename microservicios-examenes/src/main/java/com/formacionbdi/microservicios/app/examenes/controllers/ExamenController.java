package com.formacionbdi.microservicios.app.examenes.controllers;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.formacionbdi.microservicios.app.examenes.services.ExamenService;
import com.formacionbdi.microservicios.commons.controllers.CommonController;
import com.formacionbdi.microservicios.commons.examenes.models.entity.Examen;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService> {

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@RequestBody Examen examen, @PathVariable Long id) {

		Optional<Examen> o = service.findById(id);

		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}

		Examen examenDb = o.get();
		examenDb.setNombre(examen.getNombre());
		
		
		// Forma 1 para eliminar preguntas repetidas
		
		// Usando stream de java 8
		examenDb.getPreguntas()
		.stream()
		.filter(pdb -> !examen.getPreguntas().contains(pdb))
		.forEach(examenDb::removePregunta);
		

		
		// Forma 2 para eliminar pregunas repetidas
		
//		// Lista de preguntas eliminadas.
//		List<Pregunta> eliminadas = new ArrayList<>();
//
//		examenDb.getPreguntas().forEach(pdb -> {
//			if (!examen.getPreguntas().contains(pdb)) {
//				eliminadas.add(pdb);
//
//			}
//		});
//
//		// Eliminando preguntas repetidas manera corta
//		eliminadas.forEach(examenDb::removePregunta);
		
//		// Eliminando preguntas repetidas
//		eliminadas.forEach(p -> {
//			examenDb.removePregunta(p);
//		});
		
		
		
		examenDb.setPreguntas(examen.getPreguntas());

		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));

	}

}
