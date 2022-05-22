export class Race {

  public ID: number;
  public name: string;
  public place: string;
  public date: string;
  public prize: number;
  public horses: any [];
  public ISOVER: boolean;

  constructor(ID: number, name: string, place: string, date: string, prize: number, horses: any[], ISOVER: boolean) {
    this.ID = ID;
    this.name = name;
    this.place = place;
    this.date = date;
    this.prize = prize;
    this.horses = horses;
    this.ISOVER = ISOVER;
  }
}
