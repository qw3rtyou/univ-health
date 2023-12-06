package health;

import facade.DataEngineImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExerciseMgr extends DataEngineImpl<Exercise> {
	private static ExerciseMgr mgr = null;
	private List<Exercise> exercises;
	private String headers[] = { "타입", "운동", "강도", "부위", "사진" };

	private ExerciseMgr() {

		exercises = new ArrayList<>(); // 초기화
		setLabels(headers);
		readAll("data/exercise_data.txt");


	}

	public List<Exercise> getExercises() {
		return exercises;
	}




	private void readAll(String filename) {
		Scanner file = openFile(filename);
		while (file.hasNext()) {
			String type = file.next();
			String name = file.next();
			Double mets = file.nextDouble();
			String parts = null;
			if (!type.equals("유산소")) {
				parts = file.next();
			}

			String photoDir = file.next();
			exercises.add(new Exercise(type, name, mets, parts, photoDir));
		}
		file.close();
	}

	public static ExerciseMgr getInstance() {
		if (mgr == null) {
			mgr = new ExerciseMgr();
		}
		return mgr;
	}

	public List<Exercise> getExercisesByType(String type) {
		List<Exercise> result = new ArrayList<>();
		for (Exercise exercise : exercises) {
			if (exercise.getType().equals(type))
				result.add(exercise);
		}
		return result;
	}

}