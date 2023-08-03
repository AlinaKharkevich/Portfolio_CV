package com.alinaharkevich.mas_mp5;
import com.alinaharkevich.mas_mp5.model.Faculty;
import com.alinaharkevich.mas_mp5.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final FacultyRepository facultyRepository;

    @EventListener
    public void atStart(ContextRefreshedEvent event){
        System.out.println("Context refreshed");
        Iterable<Faculty> all = facultyRepository.findAll();
        System.out.println(all);
    }
}
