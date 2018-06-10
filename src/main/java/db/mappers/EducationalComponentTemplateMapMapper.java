package db.mappers;

import db.entities.Diploma;
import db.entities.EducationalComponent;
import db.entities.EducationalComponentTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ui.models.EducationalComponentWithData;
import ui.models.TemplateWithEducationalComponents;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EducationalComponentTemplateMapMapper extends
        Mapper<Map<EducationalComponentTemplate, List<EducationalComponent>>, TemplateWithEducationalComponents> {
    private EducationalComponentTemplateMapper educationalTemplateMapper;
    private EducationalComponentMapper educationalComponentMapper;
    private GroupMapper groupMapper;
    private StudentMapper studentMapper;

    @Autowired
    public EducationalComponentTemplateMapMapper(EducationalComponentTemplateMapper educationalTemplateMapper,
                                                 GroupMapper groupMapper,
                                                 EducationalComponentMapper educationalComponentMapper, StudentMapper studentMapper) {
        this.educationalTemplateMapper = educationalTemplateMapper;
        this.educationalComponentMapper = educationalComponentMapper;
        this.groupMapper = groupMapper;
        this.studentMapper = studentMapper;
    }

    @Override
    public TemplateWithEducationalComponents map(Map<EducationalComponentTemplate,
            List<EducationalComponent>> value) {

        return null;
    }

    public List<TemplateWithEducationalComponents> mapAll(Map<EducationalComponentTemplate, List<EducationalComponent>> values) {
        List<TemplateWithEducationalComponents> returnValues = new ArrayList<>(values.size());
        for (EducationalComponentTemplate value : values.keySet()) {

            final List<EducationalComponentWithData> educationalComponentWithDataList = new ArrayList<>();
            for (EducationalComponent educationalComponent :
                    values.get(value)) {
                final Diploma diploma = educationalComponent.getDiploma();
                final EducationalComponentWithData educationalComponentWithData = new EducationalComponentWithData();
                educationalComponentWithData.setGroup(groupMapper.map(diploma.getStudent().getGroup()));
                educationalComponentWithData.setEducationalComponent(
                        educationalComponentMapper.map(educationalComponent));
                educationalComponentWithData.setStudent(studentMapper.map(diploma.getStudent()));

                educationalComponentWithDataList.add(educationalComponentWithData);
            }
            final TemplateWithEducationalComponents templateWithEducationalComponents =
                    new TemplateWithEducationalComponents(educationalTemplateMapper.map(value),
                            educationalComponentWithDataList);
            returnValues.add(templateWithEducationalComponents);
        }
        return returnValues;
    }

    @Override
    public Map<EducationalComponentTemplate, List<EducationalComponent>> reverseMap(TemplateWithEducationalComponents value) {
        return null;
    }

}
