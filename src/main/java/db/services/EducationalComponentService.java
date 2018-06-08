package db.services;

import com.j256.ormlite.dao.Dao;
import db.entities.EducationalComponent;
import db.entities.EducationalComponentTemplate;
import db.entities.EducationalComponentTypeConst;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EducationalComponentService extends BaseServiceImpl<EducationalComponent> {
    private RatingPointService ratingPointService;
    private NationalGradeService nationalGradeService;
    private EducationalComponentTemplateService educationalComponentTemplateService;
    private GroupService groupService;

    public EducationalComponentService(
            Dao<EducationalComponent, Integer> dao, RatingPointService ratingPointService,
            NationalGradeService nationalGradeService, EducationalComponentTemplateService educationalComponentTemplateService, GroupService groupService) {
        super(dao);
        this.ratingPointService = ratingPointService;
        this.nationalGradeService = nationalGradeService;
        this.educationalComponentTemplateService = educationalComponentTemplateService;
        this.groupService = groupService;
    }

    @Override
    public int create(EducationalComponent educationalComponent) throws SQLException {
        int nationalScore = educationalComponent.getNationalScore();
        educationalComponent.setRatingPoint(
                ratingPointService.getRatingPointByScore(nationalScore));
        educationalComponent.setNationalGrade(
                nationalGradeService.getNationalGradeByScore(nationalScore));
        return super.create(educationalComponent);
    }

    @Override
    public int update(EducationalComponent educationalComponent) throws SQLException {
        int nationalScore = educationalComponent.getNationalScore();
        educationalComponent.setRatingPoint(
                ratingPointService.getRatingPointByScore(nationalScore));
        educationalComponent.setNationalGrade(
                nationalGradeService.getNationalGradeByScore(nationalScore));
        return super.update(educationalComponent);
    }

    public List<EducationalComponent> getAllByDiplomaId(int diplomaId) throws SQLException {
        final List<EducationalComponent> educationalComponents =
                getDao().queryForEq("diploma_id", diplomaId);

        LOGGER.info(
                String.format("The diploma with id{%d} contains %d educational component(s)",
                        diplomaId, educationalComponents.size()));

        return educationalComponents;
    }

    public List<EducationalComponent> getAllCoursesByDiplomaId(int diplomaId) throws
            SQLException {
        final List<EducationalComponent> educationalComponents = new ArrayList<>();
        for (EducationalComponent component :
                getAllByDiplomaId(diplomaId)) {
            if (component.getEducationalComponentTemplate()
                    .getEducationalComponentType()
                    .getName()
                    .equals(EducationalComponentTypeConst.COURSE)) {
                educationalComponents.add(component);
            }
        }

        LOGGER.info(
                String.format("The diploma with id{%d} contains %d course(s)", diplomaId,
                        educationalComponents.size()));

        return educationalComponents;
    }

    public List<EducationalComponent> getAllResearchProjectsByDiplomaId(int diplomaId)
            throws SQLException {
        final List<EducationalComponent> educationalComponents = new ArrayList<>();
        for (EducationalComponent component :
                getAllByDiplomaId(diplomaId)) {
            if (component.getEducationalComponentTemplate()
                    .getEducationalComponentType()
                    .getName()
                    .equals(EducationalComponentTypeConst.RESEARCH_PROJECT)) {
                educationalComponents.add(component);
            }
        }

        return educationalComponents;
    }

    public List<EducationalComponent> getAllInternshipsByDiplomaId(int diplomaId)
            throws SQLException {
        List<EducationalComponent> educationalComponents = new ArrayList<>();
        for (EducationalComponent component :
                getAllByDiplomaId(diplomaId)) {
            if (component.getEducationalComponentTemplate()
                    .getEducationalComponentType()
                    .getName()
                    .equals(EducationalComponentTypeConst.INTERNSHIP)) {
                educationalComponents.add(component);
            }
        }

        LOGGER.info(
                String.format("The diploma with id{%d} contains %d internship(s)", diplomaId,
                        educationalComponents.size()));

        return educationalComponents;
    }

    public List<EducationalComponent> getAllStateAttestationsByDiplomaId(int diplomaId)
            throws SQLException {
        List<EducationalComponent> educationalComponents = new ArrayList<>();
        for (EducationalComponent component :
                getAllByDiplomaId(diplomaId)) {
            if (component.getEducationalComponentTemplate()
                    .getEducationalComponentType()
                    .getName()
                    .equals(EducationalComponentTypeConst.STATE_ATTESTATION)) {
                educationalComponents.add(component);
            }
        }

        LOGGER.info(
                String.format("The diploma with id{%d} contains %d state attestation(s)", diplomaId,
                        educationalComponents.size()));

        return educationalComponents;
    }

    public double getCreditsGained(int diplomaId) throws SQLException {
        double value = 0;
        for (EducationalComponent component :
                getAllByDiplomaId(diplomaId)) {
            value += component.getEducationalComponentTemplate().getCredits();
        }

        LOGGER.info(
                String.format("The diploma with id{%d} contains %f credit gained", diplomaId, value));

        return value;
    }

    public Map<EducationalComponentTemplate, List<EducationalComponent>> getComponentTemplateWithDiplomas() throws SQLException {
        final List<EducationalComponentTemplate> educationalComponentTemplates =
                educationalComponentTemplateService.getAll();
        final Map<EducationalComponentTemplate, List<EducationalComponent>> educationalComponentTemplateListMap =
                new HashMap<>();
        for (EducationalComponentTemplate educationalComponentTemplate :
                educationalComponentTemplates) {
            educationalComponentTemplateListMap.put(educationalComponentTemplate,
                    getByTemplateAndDiplomaId(educationalComponentTemplate.getId()));
        }
        return educationalComponentTemplateListMap;
    }

    public List<EducationalComponent> getByTemplateAndDiplomaId(int templateId) throws SQLException {
        List<EducationalComponent> list = getDao().queryForEq("educational_component_template_id", templateId);
        for (EducationalComponent aList : list) {
            int id = aList.getDiploma().getStudent().getGroup().getId();
            aList.getDiploma().getStudent().getGroup().setName(groupService.getById(id).getName());
        }
        return list;
    }
}
