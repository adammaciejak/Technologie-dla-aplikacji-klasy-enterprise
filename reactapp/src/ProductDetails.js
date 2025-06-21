import React from 'react';
import { useParams, Link } from 'react-router-dom';

const ProductDetails = ({ products }) => {
    const { id } = useParams();
    const filtered = products.filter(product => product.id === Number(id));

    if (filtered.length === 0) return null;

    const product = filtered[0];

    return (
        <div>
            <h1>{product.title}</h1>
            Category: {product.category}<br />
            Brand: {product.brand}<br />
            Description: {product.description}<br />
            Price: {product.price}<br />
            <img src={product.thumbnail} alt={product.title} style={{ maxWidth: '300px', display: 'block', margin: '20px 0' }} />
            <Link to="/">Back to product list</Link>
        </div>
    );
};

export default ProductDetails;
