import pickle

import numpy as np
from flask import Flask, request, jsonify

# Load the pickled data
popular_df = pickle.load(open('popular.pkl', 'rb'))
pt = pickle.load(open('pt.pkl', 'rb'))
books = pickle.load(open('books.pkl', 'rb'))
similarity_scores = pickle.load(open('similarity_scores.pkl', 'rb'))

# Initialize the Flask application
app = Flask(__name__)


# Route for the home page
@app.route('/home', methods=['GET'])
def index():
    books = []
    for index, row in popular_df.iterrows():
        book = {
            'title': row['title'],
            'author': row['author'],
            'image': row['Image-URL-S'],
            'votes': row['num_rating'],
            'rating': row['avg_rating']

        }
        books.append(book)
    return jsonify({'books': books})



# Route to get book recommendations
@app.route('/recommend_books', methods=['POST'])
def recommend():
    data = request.get_json()
    book_name = data.get('book_name')

    # Find the index of the user input book in the pivot table
    if book_name in pt.index:
        index = np.where(pt.index == book_name)[0][0]

        # Calculate similarity scores and sort them
        similar_items = sorted(list(enumerate(similarity_scores[index])), key=lambda x: x[1], reverse=True)[1:5]

        recommendations = []
        for i in similar_items:
            temp_df = books[books['title'] == pt.index[i[0]]]
            book_info = {
                'title': temp_df['title'].values[0],
                'author': temp_df['author'].values[0],
                'image': temp_df['Image-URL-M'].values[0]
            }
            recommendations.append(book_info)

        return jsonify({'recommendations': recommendations})
    else:
        return jsonify({'recommendations': []})


# Run the Flask application
if __name__ == '__main__':
    app.run(debug=True)
