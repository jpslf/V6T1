package main;

import java.util.Scanner;
import java.util.ArrayList;

class Instrument {
	String manufacturer;
	int price;

	public Instrument(String manufacturer_, int price_) {
		manufacturer = manufacturer_;
		price = price_;
	}

	public String getDetails() {
		return String.format("Valmistaja: %s, Hinta: %de", manufacturer, price);
	}
}

class StringInstrument extends Instrument {
	int number_of_strings;

	public StringInstrument(String manufacturer, int price, int number_of_strings_) {
		super(manufacturer, price);

		number_of_strings = number_of_strings_;
	}

	void tune() {
		System.out.println(String.format("%s soittimesta viritettiin %d kieltä!", manufacturer, number_of_strings));
	}
}

class Guitar extends StringInstrument {
	public Guitar(String manufacturer, int price) {
		super(manufacturer, price, 6);
	}
}

class Violin extends StringInstrument {
	public Violin(String manufacturer, int price) {
		super(manufacturer, price, 4);
	}
}

class Drum extends Instrument {
	public Drum(String manufacturer, int price) {
		super(manufacturer, price);
	}

	void playBeat() {
		System.out.println(String.format("%s rummut soittavat komppia!", manufacturer));
	}
}

class Impl {
	Scanner in;

	ArrayList<Instrument> instruments;

	Impl(String[] args) {
		in = new Scanner(System.in);
	}

	String nextString() {
		return in.nextLine();
	}

	String promtString(String promt) {
		System.out.println(promt);

		return nextString();
	}

	int nextInt() {
		return Integer.parseInt(nextString());
	}

	int promtInt(String promt) {
		System.out.println(promt);

		return nextInt();
	}

	void close() {
		in.close();

		System.out.println("Kiitos ohjelman käytöstä.");
	}

	void LisaaSoitin() {
		int instrument = promtInt("Minkä soittimen haluat lisätä? 1) Kitara, 2) Viulu, 3) Rummut");

		String manufacturer = promtString("Anna valmistajan nimi:");

		int price = promtInt("Anna hinta euroina:");

		switch (instrument) {
			case 1:
				instruments.add(new Guitar(manufacturer, price));

				break;

			case 2:
				instruments.add(new Violin(manufacturer, price));

				break;

			case 3:
				instruments.add(new Drum(manufacturer, price));

				break;

			default:
				System.out.println("Virheellinen soitinvalinta.");

				return;
		}

		System.out.println("Soitin lisätty listaan! ");
	}

	void ListaaSoittimet() {
		for (Instrument i : instruments) {
			System.out.println(i.getDetails());
		}
	}

	void ViritaKielisoittimet() {
		for (Instrument i : instruments) {
			if (i instanceof StringInstrument) {
				((StringInstrument) i).tune();
			}
		}
	}

	void SoitaRumpuja() {
		for (Instrument i : instruments) {
			if (i instanceof Drum) {
				((Drum) i).playBeat();
			}
		}
	}

	boolean run() {
		System.out.println("1) Lisää soitin");
		System.out.println("2) Listaa soittimet");
		System.out.println("3) Viritä kielisoittimet");
		System.out.println("4) Soita rumpuja");

		System.out.println("0) Lopeta ohjelma");

		int selection = Integer.parseInt(in.nextLine());

		if (selection == 0) {
			return false;

		} else if (selection == 1) {
			LisaaSoitin();

		} else if (selection == 2) {
			ListaaSoittimet();

		} else if (selection == 3) {
			ViritaKielisoittimet();

		} else if (selection == 4) {
			SoitaRumpuja();

		} else {
			System.out.println("Tuntematon valinta, yritä uudestaan.");
		}

		return true;
	}
}

public class App {
	public static void main(String[] args) {
		Impl impl = new Impl(args);

		while (impl.run()) {}

		impl.close();
	}
}
