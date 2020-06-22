package judge.service;

import judge.model.service.HomeworkServiceModel;
import judge.repository.HomeworkRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public HomeworkServiceImpl(HomeworkRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public void addHomework(HomeworkServiceModel homeworkServiceModel) {

    }
}
