export class User {

  public ID: number;
  public username: string;
  public balance: number

  constructor(ID: number, username: string, balance: number) {
    this.ID = ID;
    this.username = username;
    this.balance = balance;
  }
}
