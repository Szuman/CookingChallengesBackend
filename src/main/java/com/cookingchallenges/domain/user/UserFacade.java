package com.cookingchallenges.domain.user;

import com.cookingchallenges.domain.user.dto.UserDTO;
import com.cookingchallenges.domain.user.exception.UserNotFoundException;
import com.cookingchallenges.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserDAO userDao;

//    @Transactional
//    public Long makeBooking(MakeBooking makeBooking) {
//        Content content = contentFacade.getScreeningIfStillAvailable(makeBooking.getScreeningId());
//        List<ReservationDTO> reservationDTOs = makeBooking.getReservations();
//        List<Reservation> reservations = reservationService.makeReservations(reservationDTOs, content);
//        return userDao.save(new User(makeBooking.getName(), makeBooking.getSurname(), content, reservations));
//    }

//    public BookingDTO getBooking(Long id) {
//        User user = userDao.findById(id).orElseThrow(() ->
//                new BookingNotFoundException("Booking (id=" + id +") does not exist"));
//        return UserMapper.map(user);
//    }
//---------------------------------------------------------------------------------------------------
    public UserDTO getUserById(Long id) {
        User user = userDao.findById(id).orElseThrow(() ->
                new UserNotFoundException("User (id=" + id +") does not exist"));
        return UserMapper.map(user);
    }

    public UserDTO getUserByName(String name) {
        User user = userDao.findByName(name).orElseThrow(() ->
                new UserNotFoundException("User (name=" + name +") does not exist"));
        return UserMapper.map(user);
    }

}