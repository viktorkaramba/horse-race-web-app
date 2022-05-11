export interface IRace{
  id: number,
  name: string,
  place: string,
  date: string,
  prize: number,
  horses: any []
}

export class Race implements IRace {

  public id: number;
  public name: string;
  public place: string;
  public date: string;
  public prize: number;
  public horses: any [];
  public isOver: boolean;

  constructor(id: number, name: string, place: string, date: string, prize: number, horses: any[], isOver: boolean) {
    this.id = id;
    this.name = name;
    this.place = place;
    this.date = date;
    this.prize = prize;
    this.horses = horses;
    this.isOver = isOver;
  }
}
