package colval.h22.todolist.services;

import colval.h22.todolist.models.entities.Item;
import colval.h22.todolist.models.entities.ItemDate;
import colval.h22.todolist.models.interfaces.InterfaceDateService;
import colval.h22.todolist.repositories.DateRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class DateService implements InterfaceDateService {
    private final DateRepository dateRepository;

    public DateService(DateRepository dateRepository) {
        this.dateRepository = dateRepository;
    }

    @Override
    public ItemDate create(ItemDate date) {
        return dateRepository.save(date);
    }

    @Override
    public ItemDate getIfExists(ItemDate date) {
        var dateFound =
                dateRepository.getByDate(
                        date.getYear(),
                        date.getMonth(),
                        date.getDay()
                );

        if (dateFound.isPresent())
            return dateFound.orElseThrow();
        else
            return null;
    }

    @Override
    public ItemDate update(ItemDate date) {
        var dateFound = read(date.getId());

        dateFound.setYear(date.getYear());
        dateFound.setMonth(date.getMonth());
        dateFound.setDay(date.getDay());
        dateFound.setItems(date.getItems());

        return dateRepository.save(dateFound);
    }

    @Override
    public ItemDate read(Long id) {
        return dateRepository.findById(id).orElse(null);
    }

    @Override
    public Optional<ItemDate> getByDate(int year, int month, int day) {
        return dateRepository.getByDate(year, month, day);
    }

    @Override
    public ItemDate getOrCreate(int year, int month, int day) {
        var dateFound = getIfExists(new ItemDate(year, month, day));

        if (dateFound != null) {
            return dateFound;
        } else {
            return create(new ItemDate(year, month, day));
        }
    }
}