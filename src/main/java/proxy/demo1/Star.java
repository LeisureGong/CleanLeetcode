package proxy.demo1;

/**
 * @author gonglei
 * @date 2020/8/5
 */
public class Star implements ShowService{

	private String name;

	@Override
	public void sing(String songName) {
		System.out.println(this.name + "sing  a song: " + songName);
	}

	@Override
	public  void dance() {
		System.out.println(this.name + "dance");
	}


	public Star(String name) {
		this.name = name;
	}

}
