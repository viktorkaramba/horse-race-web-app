export class Coefficient {

  public id: number;
  public idRace: number;
  public idHorse: number;
  public value: number;


  constructor(id: number, idRace: number, idHorse: number, value: number) {
    this.id = id;
    this.idRace = idRace;
    this.idHorse = idHorse;
    this.value = value;
  }
}
