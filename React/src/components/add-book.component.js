import React, { Component } from "react";
import BookDataService from "../services/book.service";


export default class AddBook extends Component {  
    constructor(props) {
    super(props);
    this.onChangeName = this.onChangeName.bind(this);
    this.onChangeAuthor = this.onChangeAuthor.bind(this);
    this.onChangePage = this.onChangePage.bind(this);
    this.saveBook = this.saveBook.bind(this);
    this.newBook = this.newBook.bind(this);

    this.state = {
      id: null,
      name: "",
      author: "", 
      page: "",
      submitted: false
    };
  }

  onChangeName(e) {
    this.setState({
      name: e.target.value
    });
  }

  onChangeAuthor(e) {
    this.setState({
      author: e.target.value
    });
  }

  onChangePage(e){
      this.setState({
        page: e.target.value
    });
  }

  saveBook() {
    var data = {
      name: this.state.name,
      author: this.state.author,
      page: this.state.page
    };

    BookDataService.create(data)
      .then(response => {
        this.setState({
          id: response.data.id,
          name: response.data.name,
          author: response.data.author,
          page: response.data.page,

          submitted: true
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }

  newBook() {
    this.setState({
      id: null,
      name: "",
      author: "",
      page: "",

      submitted: false
    });
  }

  render() {
    return (
      <div className="submit-form">
        {this.state.submitted ? (
          <div>
            <h4>You submitted successfully!</h4>
            <button className="btn btn-success" onClick={this.newBook}>
              Add
            </button>
          </div>
        ) : (
          <div>
            <div className="form-group">
              <label htmlFor="name">Name</label>
              <input
                type="text"
                className="form-control"
                id="name"
                required
                value={this.state.name}
                onChange={this.onChangeName}
                name="name"
              />
            </div>

            <div className="form-group">
              <label htmlFor="author">Author</label>
              <input
                type="text"
                className="form-control"
                id="author"
                required
                value={this.state.author}
                onChange={this.onChangeAuthor}
                name="author"
              />
            </div>

            <div className="form-group">
              <label htmlFor="page">Page</label>
              <input
                type="text"
                className="form-control"
                id="page"
                required
                value={this.state.page}
                onChange={this.onChangePage}
                name="page"
              />
            </div>
            <button onClick={this.saveBook} className="btn btn-success">
              Submit
            </button>
          </div>
        )}
      </div>
    );
  }
}