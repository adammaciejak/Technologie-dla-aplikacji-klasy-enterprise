import React, { useState } from 'react';
import ProductItem from './ProductItem';

const ProductList = ({ products }) => {
    const [filter, setFilter] = useState("");

    return (
        <div>
            <h1>List of products</h1>
            <label>
                Filter by product title: 
                <input
                    type="text"
                    value={filter}
                    onChange={e => setFilter(e.target.value)}
                />
            </label>
            <ul>
                {products
                    .filter(product => product.title.toLowerCase().includes(filter.toLowerCase()))
                    .map(product => (
                        <ProductItem key={product.id} id={product.id} title={product.title} brand={product.brand} />
                    ))}
            </ul>
        </div>
    );
};

export default ProductList;