import { FC } from 'react';
import { features } from './constants';
import styles, { layout } from '../../styles';
import FeatureCard from './FeatureCard';

const FeatureList: FC = () => (
    <div className={`${layout.sectionImg} flex-col`}>
        {features.map((feature, index) => (
            <FeatureCard key={feature.id} {...feature} isLast={index === features.length - 1} />
        ))}
    </div>
);

export default FeatureList;