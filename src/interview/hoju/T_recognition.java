package interview.hoju;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.TreeSet;

public class T_recognition {
	private static final double chanceRandom = 0.5;
	private Set<H_Base> h = new TreeSet<H_Base>();
	private Set<H_Base> t = new TreeSet<H_Base>();
	private Set<H_Base> b = new TreeSet<H_Base>();
	private Set<H_Base> a = new TreeSet<H_Base>();
	private Set<H_Base> z = new TreeSet<H_Base>();
	private Set<H_Base> x = new TreeSet<H_Base>();
	private Set<H_Base> q = new TreeSet<H_Base>();

	

	public void plusH(H_Base h_Base) {
		h.add(h_Base); // 이 섬에 있는 모든 사람은 H 로 보인다
		// 당신은 T다. 나는 사람0으로 제일 먼저 들어갈 것이다.
	}

	public void meet(H_Base h_Base) {
		q.remove(h_Base);
		x.remove(h_Base);
		plusA(h_Base); // 당신이 만나는 모든 B는 A 이며
		if (random())
			plusZ(h_Base); // Z로도 간주된다.
	}

	public void plusX(H_Base h_Base) {
		plusH(h_Base); // A 나 T 가 아닌 모든 사람은(젤 처음엔 날 제외하고) 모두 X이고
		x.add(h_Base);
		if (random())
			plusQ(h_Base);// Q로도 간주될 수 있다
	}

	public void plusQ(H_Base h_Base) {
		if (!h_Base.getName().equals("사람0")) { // 당신은 절대 Q가 될 수 없다.
			q.add(h_Base);
		}
	}

	public void randomMeet(H_Base h_Base) {
		if (random() && random()) {
			meet(h_Base);
			System.out.println(h_Base.getName() + "과 만났습니다");
		}

	}

	public void plusMe(H_Base h_Base) {
		plusH(h_Base);
		plusT(h_Base);
	}

	private void plusT(H_Base h_Base) {
		if (h_Base.getName().equals("사람0")) {
			t.add(h_Base);
		}
	}

	private void plusZ(H_Base h_Base) {
		z.add(h_Base);
	}

	private void plusA(H_Base h_Base) {
		x.remove(h_Base);
		a.add(h_Base);
	}

	public boolean random() {
		return Math.random() > chanceRandom;
	}

	public Set<H_Base> getB() {
		return b;
	}

	public Set<H_Base> getA() {
		return a;
	}

	public Set<H_Base> getZ() {
		return z;
	}

	public Set<H_Base> getX() {
		return x;
	}

	public Set<H_Base> getQ() {
		return q;
	}

	public Set<H_Base> getH() {
		return h;
	}

	public Set<H_Base> getT() {
		return t;
	}

	public String showList(Set<H_Base> list) {
		return "사람명단 \n" + list.toString() + "\n총 인원수 :" + list.size();
	}

	public String showH() {
		return "사람명단 \n" + h.toString();
	}

	public String showZ() {
		return "사람명단 \n" + z.toString();
	}

	public String showT() {

		return "사람명단 \n" + t.toString();
	}

	@SuppressWarnings("unchecked")
	public void showAll(Object obj) {
		Class<?> clazz = T_recognition.class;
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			try {
				System.out.println(field.getName() + "명단\n" + ((Set<H_Base>) field.get(obj)).toString());
				System.out.println("총 인원수 : " + ((Set<H_Base>) field.get(obj)).size());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

	}

	public void comesNight(H_Base onlyOne) {
		t.remove(onlyOne);
		z.add(onlyOne);
	}

	public void comesDay(H_Base onlyOne) {
		z.remove(onlyOne);
		t.add(onlyOne);
	}
	public void clearAll() {
		h.clear();
		t.clear();
		b.clear();
		a.clear();
		z.clear();
		x.clear();
		q.clear();
	}

}