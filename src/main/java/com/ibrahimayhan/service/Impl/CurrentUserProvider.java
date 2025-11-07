package com.ibrahimayhan.service.Impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.ibrahimayhan.model.User;
import com.ibrahimayhan.repository.UserRepository;
import com.ibrahimayhan.service.ICurrentUserProvider;
import com.ibrahimayhan.exception.BaseException;
import com.ibrahimayhan.exception.ErrorMessage;
import com.ibrahimayhan.exception.MessageType;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CurrentUserProvider implements ICurrentUserProvider {

    private final UserRepository userRepository;

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated()) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "Kullanıcı doğrulanmadı"));
        }

        Object principal = auth.getPrincipal();

        // Eğer User nesnesi doğrudan principal ise
        if (principal instanceof User user) {
            return user;
        }

        // Eğer principal sadece username (String) ise
        String username = auth.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new BaseException(
                        new ErrorMessage(MessageType.USERNAME_IS_NOT_FOUND, username)));
    }
		    /*Eğer principal bir UserDetails nesnesiyse → onun getUsername() metodunu çağırır.
		
			Eğer principal bir String (örneğin username) ise → doğrudan onu döner.
			
			Bu yüzden her durumda auth.getName() sana username döner */
    
    
}


