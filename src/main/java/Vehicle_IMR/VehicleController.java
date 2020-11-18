package Vehicle_IMR;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;



// tag::constructor[]

@RestController
@CrossOrigin(allowedHeaders = "*")
class VehicleController {

	private final VehicleRepository repository;

	private final EmployeeModelAssembler assembler;

	VehicleController(VehicleRepository repository, EmployeeModelAssembler assembler) {

		this.repository = repository;
		this.assembler = assembler;
	}
	// end::constructor[]

	// Aggregate root

	// tag::get-aggregate-root[]
	@GetMapping("/vehicles")
	CollectionModel<EntityModel<Vehicle>> all() {

		List<EntityModel<Vehicle>> employees = repository.findAll().stream() //
				.map(assembler::toModel) //
				.collect(Collectors.toList());

		return CollectionModel.of(employees, linkTo(methodOn(VehicleController.class).all()).withSelfRel());
	}
	
	
	@PostMapping("/vehicles")
	ResponseEntity<?> newVehicle(@RequestBody Vehicle newVehicle) {

		EntityModel<Vehicle> entityModel = assembler.toModel(repository.save(newVehicle));
		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}
	// end::post[]

	/*@PostMapping("/vehicles")
	public void createVehicle(@RequestBody Vehicle vehicle) {
		repository.save(vehicle);

		}*/

	
	@GetMapping("/vehicles/{id}")
	EntityModel<Vehicle> one(@PathVariable Long id) {

		Vehicle vehicle = repository.findById(id) //
				.orElseThrow(() -> new VehicleNotFoundException(id));

		return assembler.toModel(vehicle);
	}
	// end::get-single-item[]

	// tag::put[]
	@PutMapping("/vehicles/{id}")
	ResponseEntity<?> replaceEmployee(@RequestBody Vehicle newVehicle, @PathVariable Long id) {

		Vehicle updatedVehicle = repository.findById(id) //
				.map(employee -> {
					employee.setLatitude(newVehicle.getLatitude());
					employee.setLongitude(newVehicle.getLongitude());
					employee.setVehicleNumber(newVehicle.getVehicleNumber());
					return repository.save(employee);
				}) //
				.orElseGet(() -> {
					newVehicle.setId(id);
					return repository.save(newVehicle);
				});

		EntityModel<Vehicle> entityModel = assembler.toModel(updatedVehicle);

		return ResponseEntity //
				.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()) //
				.body(entityModel);
	}
	// end::put[]

	// tag::delete[]
	@DeleteMapping("/vehicles/{id}")
	ResponseEntity<?> deleteVehicles(@PathVariable Long id) {

		repository.deleteById(id);

		return ResponseEntity.noContent().build();
	}
	// end::delete[]
	
	@GetMapping("/vehiclesCount/{vehicleNumber}")
	public List<Vehicle> getEmployee(@PathVariable String vehicleNumber,@RequestParam int number) {
		List<Vehicle> v = new ArrayList<Vehicle>();
		
		List<Vehicle> vehicleList = repository.findAll();
		
		Vehicle e=null;
		int i=0;
		int no= number;
		
			for(Vehicle vehicle : vehicleList) {
					if(vehicle.getVehicleNumber().equals(vehicleNumber) && i!=no) {
						e=vehicle;
						v.add(e);
						i++;
					}
				
			}
			
		return v;
		
	}
	
	@GetMapping("/vehiclesList")
	public HashSet<String> vehicleList() {
		List<Vehicle> vehicleList1 = repository.findAll();
		HashSet<String> v = new HashSet<String>();
		String e;
		
		for(Vehicle vehicle : vehicleList1) {
			e = vehicle.getVehicleNumber();
			v.add(e);
		}
		return v;
	}
	
	@GetMapping("/currentLocation/{vehicleNumber}")
	public Vehicle currentLocation(@PathVariable String vehicleNumber) {
		List<Vehicle> vehicleList1 = repository.findAll();
		List<Vehicle> v = new ArrayList<Vehicle>();
		
		for(Vehicle vehicle : vehicleList1) {
			if(vehicle.getVehicleNumber().equals(vehicleNumber)) {
				v.add(vehicle);
			}
		}
		Vehicle e = v.get(v.size()-1);
		return e;
		
	}
}
