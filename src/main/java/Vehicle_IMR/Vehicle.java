package Vehicle_IMR;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="Vehicle")
class Vehicle {

	private @Id @GeneratedValue Long id;
	private double latitude;
	private double longitude;
	private String vehicleNumber;
	private Timestamp timastamp;
	
	Vehicle(){
		
	}
	
	public Vehicle(Long id, double latitude, double longitude, String vehicleNumber, Timestamp timastamp) {
		super();
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.vehicleNumber = vehicleNumber;
		this.timastamp = timastamp;
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		long temp;
		temp = Double.doubleToLongBits(latitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(longitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((timastamp == null) ? 0 : timastamp.hashCode());
		result = prime * result + ((vehicleNumber == null) ? 0 : vehicleNumber.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (Double.doubleToLongBits(latitude) != Double.doubleToLongBits(other.latitude))
			return false;
		if (Double.doubleToLongBits(longitude) != Double.doubleToLongBits(other.longitude))
			return false;
		if (timastamp == null) {
			if (other.timastamp != null)
				return false;
		} else if (!timastamp.equals(other.timastamp))
			return false;
		if (vehicleNumber == null) {
			if (other.vehicleNumber != null)
				return false;
		} else if (!vehicleNumber.equals(other.vehicleNumber))
			return false;
		return true;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public Timestamp getTimastamp() {
		return timastamp;
	}

	public void setTimastamp(Timestamp timastamp) {
		this.timastamp = timastamp;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + ", vehicleNumber="
				+ vehicleNumber + ", timastamp=" + timastamp + "]";
	}

}
