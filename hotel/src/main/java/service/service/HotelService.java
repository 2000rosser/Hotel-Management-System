package service.service;

import service.core.AbstractQuotationService;
import service.core.RoomInfo;
import service.core.Quotation;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

import org.springframework.stereotype.Service;

import service.model.Room;




@Service
public class HotelService extends AbstractQuotationService {
	// All references are to be prefixed with an AF (e.g. AF001000)
	public static final String PREFIX = "AF";
	public static final String COMPANY = "Auld Fellas Ltd.";

	@Autowired
    public HotelService(RoomService roomService) {
		this.roomService = roomService;
		System.out.println("HotelService is created, roomService is: " + roomService);
    }


	private RoomService roomService;

	public Quotation generateQuotation(RoomInfo roomInfo) {
	
		if (roomInfo == null) {
			throw new IllegalArgumentException("RoomInfo cannot be null.");
		}

		List<Room> rooms = roomService.getAllRooms();

		if (rooms == null) {
			throw new IllegalArgumentException("Rooms cannot be null.");
		}
		for (Room room : rooms) {
			if (roomInfo.type.equals(room.getType()) && roomInfo.beds == room.getBeds() && roomInfo.bedSize == room.getBedType() && roomInfo.balcony == room.isBalcony() && roomInfo.view.equals(room.getView()) && roomInfo.accessibility == room.isAccessible()) {
				return new Quotation(COMPANY, generateReference(PREFIX), room.getPrice());
			}
		}

		return null;
	}
	
}
