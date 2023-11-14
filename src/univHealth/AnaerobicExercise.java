package univHealth;

/*
 * 처음에 중량, 몇 세트, 한 횟수 등을 고려하여 칼로리 계산을 하자는 의견이 있었는데,
 * 검색해보니 해당 값들을 이용해 칼로리 계산을 하는건 현실적으로 매우 어렵다고 함
 * 만약 위와 같은 값들로 칼로리 계산을 하려고 하면
 * 1. 위의 요인들을 고려한 계산식이 필요
 * 2. 해당 계산식을 활용하여 다양한 운동했을 때 나온 칼로리 데이터가 필요
 * 둘 다 검색해도 없어서 그냥 시간 당 대략적인 칼로리 소모로 계획 변경
 */

public class AnaerobicExercise extends Exercise {
	protected String part;

	public AnaerobicExercise(String name, String type, int caloriesBurnedPerMinute, String part) {
		super(name, type, caloriesBurnedPerMinute);
		this.part = part;
	}

	@Override
	public String toString() {
		return super.toString()+"부위 : "+part+"\n";
	}
}