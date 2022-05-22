export class Coefficient {

  public ID: number;
  public IDRA: number;
  public IDHO: number;
  public value: number;


  constructor(ID: number, IDRA: number, IDHO: number, value: number) {
    this.ID = ID;
    this.IDRA = IDRA;
    this.IDHO = IDHO;
    this.value = value;
  }
}
