package globalvar;



/**
 * Contains all the properties that the structure of the survey can hold
 * @author Alexandru
 *
 */
public interface StructDefinitionElements {

	public final String PERSON="PERSON";
	public final String QUESTION="QUESTION";
	public final String SURVEY="SURVEY";
	public final String GENERAL="GENERAL";
	public final String INSTANCE="INSTANCE";
	public final String SGD="SGD";
	public final String OTHER="OTHER";
	public final String [] TYPES = {PERSON, QUESTION, SURVEY, GENERAL, INSTANCE, SGD, OTHER};
}
