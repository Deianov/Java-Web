package judge.service;

import judge.constant.Constants;
import judge.exception.AlreadyExistsException;
import judge.model.entity.Exercise;
import judge.model.service.ExerciseServiceModel;
import judge.repository.ExerciseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseRepository repository;
    private final ModelMapper mapper;

    @Autowired
    public ExerciseServiceImpl(ExerciseRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public void create(ExerciseServiceModel exerciseServiceModel) {

        if(repository.existsByName(exerciseServiceModel.getName())) {
            throw new AlreadyExistsException(Constants.EXERCISE_NAME_FIELD, Constants.EXERCISE_NAME_EXISTS_MESSAGE);
        }

        Exercise exercise = mapper.map(exerciseServiceModel, Exercise.class);
        repository.saveAndFlush(exercise);
    }
}
